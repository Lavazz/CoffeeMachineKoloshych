package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.dao.exception.WrongLoginDAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.entity.UserStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLUserDAO implements UserDAO {
    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLCartUserDAO.class);

    public SQLUserDAO() {
    }

    @Override
    public User logIn(String userLogin, String userPassword) throws DAOException, WrongAuthorizationDataException {
        User user;
        ResultSet rs = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CHECK_USER)) {
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);

            rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            } else {
                throw new WrongAuthorizationDataException("This user is not founded");
            }
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public User registration(String login, String password, String email, String name) throws DAOException {
        ResultSet rs;
        int idUser = 0;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_REGISTER_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, name);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return new User(idUser, login, password, email, name, UserStatus.CUSTOMER);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean updateUserPassword(User user, String newPassword) throws DAOException {

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_UPDATE_PASSWORD)) {
            ps.setString(1, newPassword);
            ps.setInt(2, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL updateUserPassword" + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }


    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> listUser = new ArrayList<>();
        ResultSet rs = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_USER_ALL)) {
            rs = ps.executeQuery();

            while (rs.next()) {
                listUser.add(createUser(rs));
            }
            return listUser;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public boolean checkId(int idUser) throws DAOException {
        ResultSet rs = null;
        try ( Connection con = pool.getConnection();
              PreparedStatement ps = con.prepareStatement(QUERY_CHECK_USER_ID)){
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        ResultSet rs = null;
        User user = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_USER_BY_LOGIN)) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public String getPassword(int idUser) throws DAOException {
        ResultSet rs = null;
        String password = null;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_PASSWORD)) {
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString(PARAMETER_PASSWORD);
            }
            return password;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }


    @Override
    public boolean checkUserExists(String login) throws DAOException {
        ResultSet rs = null;
        boolean result = false;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_USER_EXISTS_CHECK)) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
            return result;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }


    @Override
    public User getUserById(int idUser) throws DAOException {
        ResultSet rs = null;
        User user = null;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CHECK_USER_ID)) {
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }
            return user;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public int getIdUserByLogin(String login) throws DAOException, WrongLoginDAOException {
        ResultSet rs = null;
        int idUser;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_USER_GET_ID)) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = Integer.parseInt(rs.toString());
            } else {
                throw new WrongLoginDAOException("This user is not exists");
            }
            return idUser;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    private static User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(PARAMETER_ID_USER));
        user.setEmail(rs.getString(PARAMETER_EMAIL));
        user.setLogin(rs.getString(PARAMETER_LOGIN));
        user.setPassword(rs.getString(PARAMETER_PASSWORD));
        user.setName(rs.getString(PARAMETER_NAME));
        String status = rs.getString(PARAMETER_STATUS);
        UserStatus userStatus = UserStatus.valueOf(status.toUpperCase());
        user.setUserStatus(userStatus);

        return user;
    }

    @Override
    public int getIdUserByOrder(int idOrder) throws DAOException {
        ResultSet rs = null;
        int idUser;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_USER_BY_ORDER)) {
            ps.setInt(1, idOrder);
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt(PARAMETER_ID_USER);
            } else {
                throw new DAOException("This user does not exist");
            }
            return idUser;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }


}
