package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import by.trjava.kaloshych.entity.OrderJournal;
import by.trjava.kaloshych.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLUserDAO implements UserDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLUserDAO() {
    }

    @Override
    public User authorization(String userLogin, String userPassword) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_CHECK_USER);
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);

            rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            } else {
                throw new DAOException("This user is not founded");
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public boolean removeUser(String login) throws DAOException {
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();

            if (rs.next()) {
                ps2 = con.prepareStatement(QUERY_DELETE_LOGIN);
                ps2.setString(1, login);
                result = ps2.executeUpdate() > 0;
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }


    @Override
    public boolean registration(String userEmail, String userLogin, String userPassword, String userName) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        boolean result;

        try {
            con = pool.getConnection();

            ps = con.prepareStatement(QUERY_USER_EXISTS_CHECK);
            ps.setString(1, userLogin);
            rs = ps.executeQuery();

            ps2 = con.prepareStatement(QUERY_REGISTER_USER);
            if (!rs.next()) {
                ps2.setString(1, userEmail);
                ps2.setString(2, userLogin);
                ps2.setString(3, userPassword);
                ps2.setString(4, userName);
                result = ps2.executeUpdate() > 0;
            } else {
                return false; // user already exists //SQLUtils.close/RETURN THE CONNECTION
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_UPDATE_PASSWORD);
            ps.setString(1, newPassword);
            ps.setString(2, login);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(ps, con);
        }
    }


    @Override
    public List<User> listAllUsers() throws DAOException {
        List<User> listUser = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_USER_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                listUser.add(createUser(rs));
            }
            return listUser;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public boolean checkId(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_CHECK_USER_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public int getIdUserByLogin(String login) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idUser = 0;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_USER_GET_ID);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = Integer.parseInt(rs.toString());
            } else {
                throw new DAOException("This user is not exists");
            }
            return idUser;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    private static User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(PARAMETER_ID_USER));
        user.setEmail(rs.getString(PARAMETER_EMAIL));
        user.setLogin(rs.getString(PARAMETER_LOGIN));
        user.setPassword(rs.getString(PARAMETER_PASSWORD));
        user.setName(rs.getString(PARAMETER_NAME));
        user.setStatus(rs.getInt(PARAMETER_ID_STATUS));

        return user;
    }

    @Override
    public int getIdUserByOrder(OrderJournal orderJournal) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idUser = 0;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ORDER_JOURNAL_GET_USER);
            ps.setInt(1, orderJournal.getIdOrder());
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt(PARAMETER_ID_USER);
            } else {
                throw new DAOException("This user is not exists");
            }
            return idUser;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

}
