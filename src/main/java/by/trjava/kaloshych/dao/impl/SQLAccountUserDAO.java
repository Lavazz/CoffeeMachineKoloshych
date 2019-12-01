package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.PARAMETER_ID_ACCOUNT_USER;

public class  SQLAccountUserDAO implements AccountUserDAO {

    private static final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public AccountUser createAccountUser(User user) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idAccountUser = 0;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_ADD_ACCOUNT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idAccountUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return new AccountUser(idAccountUser, user);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public AccountUser getAccountUser(User user) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idAccountUser = 0;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_ACCOUNT_USER);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                idAccountUser = rs.getInt(PARAMETER_ID_ACCOUNT_USER);
            }
            return new AccountUser(idAccountUser, user);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }


}
