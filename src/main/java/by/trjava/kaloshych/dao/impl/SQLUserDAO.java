package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

/**
 * Represents methods for operation with User Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see User
 * @since JDK1.0
 */
public class SQLUserDAO implements UserDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int registration(String login, String password, String email, String name) throws DAOException {
        ResultSet rs;
        int idUser;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_REGISTER_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, name);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            } else {
                throw new DAOException("Exception in User SQL can't register user");
            }
            return idUser;
        } catch (SQLException e) {
            throw new DAOException("Exception in User SQL can't register user" + e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws DAOException {
        User user;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_USER_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = Creator.getInstance().createUser(rs);
                } else {
                    throw new DAOException("Exception in User SQL can't get user by login");
                }
                return user;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in User SQL can't get user by login" + e);
        }
    }

    @Override
    public User getUserById(int idUser) throws DAOException {
        User user;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CHECK_USER_ID)) {
            ps.setInt(1, idUser);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = Creator.getInstance().createUser(rs);
                } else {
                    throw new DAOException("Exception in User SQL can't get user by login");
                }
                return user;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in User SQL can't get  user" + e);
        }
    }


    @Override
    public boolean updateUserPassword(int idUser, String newPassword) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_UPDATE_PASSWORD)) {
            ps.setString(1, newPassword);
            ps.setInt(2, idUser);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Exception in SQL updateUserPassword" + e);
        }
    }

    @Override
    public boolean checkUserExists(String login) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_USER_EXISTS_CHECK)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in User SQL can't check if users exists" + e);
        }
    }

}