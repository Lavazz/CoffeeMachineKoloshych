package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.dao.util.JDBCShutter;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.configuration.SQLQuery.*;

/**
 * Represents methods for operation with AccountUser Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see AccountUser
 * @since JDK1.0
 */
public class SQLAccountUserDAO implements AccountUserDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int addAccountUser(User user) throws DAOException {
        ResultSet rs = null;
        int idAccountUser = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADD_ACCOUNT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idAccountUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return idAccountUser;
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't create accountUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public AccountUser getAccountUser(User user) throws DAOException {
        return getAccountUserById(user.getId(), QUERY_GET_ACCOUNT_USER_BY_USER);

    }

    @Override
    public AccountUser getAccountUser(int idAccountUser) throws DAOException {
        return getAccountUserById(idAccountUser, QUERY_GET_ACCOUNT_USER);
    }


    private AccountUser getAccountUserById(int id, String query) throws DAOException {
        ResultSet rs = null;
        AccountUser accountUser = null;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                accountUser = Creator.getInstance().createAccountUser(rs);
            }
            return accountUser;
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }
}
