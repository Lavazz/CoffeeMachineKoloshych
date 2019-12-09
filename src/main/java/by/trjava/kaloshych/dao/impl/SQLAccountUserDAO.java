package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;

public class SQLAccountUserDAO implements AccountUserDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public int addAccountUser(User user) throws DAOException {
        ResultSet rs = null;
        int idAccountUser=0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
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
        ResultSet rs = null;
        AccountUser accountUser = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ACCOUNT_USER_BY_USER)) {
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                accountUser = SQLUtil.getInstance().createAccountUser(rs);
            }
            return accountUser;
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public AccountUser getAccountUser(int idAccountUser) throws DAOException {
        ResultSet rs = null;
        AccountUser accountUser = null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ACCOUNT_USER)) {
            ps.setInt(1, idAccountUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                accountUser = SQLUtil.getInstance().createAccountUser(rs);
            }
            return accountUser;
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

}
