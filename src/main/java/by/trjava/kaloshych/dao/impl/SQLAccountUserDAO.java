package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;

public class SQLAccountUserDAO implements AccountUserDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public AccountUser createAccountUser(User user) throws DAOException {
        ResultSet rs = null;
        int idAccountUser = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ADD_ACCOUNT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idAccountUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return new AccountUser(idAccountUser, user);
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't create accountUser "+e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public AccountUser getAccountUser(User user) throws DAOException {
        ResultSet rs = null;
        int idAccountUser = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ACCOUNT_USER_BY_USER)) {
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                idAccountUser = rs.getInt(PARAMETER_ID_ACCOUNT_USER);
            }
            return new AccountUser(idAccountUser, user);
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser "+e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public AccountUser getAccountUser(int idAccountUser) throws DAOException {
        final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        ResultSet rs = null;
        int idUser = 0;
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ACCOUNT_USER)) {
            ps.setInt(1, idAccountUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt(PARAMETER_ID_USER);
            }
            return new AccountUser(idAccountUser, userDAO.getUserById(idUser));
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser "+e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

}
