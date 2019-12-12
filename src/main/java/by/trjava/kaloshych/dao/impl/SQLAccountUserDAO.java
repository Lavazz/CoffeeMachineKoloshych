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

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

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
    public int addAccountUser(int idUser) throws DAOException {
        int idAccountUser = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADD_ACCOUNT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUser);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    idAccountUser = rs.getInt(PARAMETER_COLUMN_INDEX);
                }
                return idAccountUser;
            }
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't create accountUser " + e);
        }
    }


    @Override
    public int getIdAccountUser(int idUser) throws DAOException {
        return getAccountUserById(idUser, QUERY_GET_ACCOUNT_USER_BY_USER).getIdAccountUser();

    }

    @Override
    public AccountUser getAccountUser(int idAccountUser) throws DAOException {
        return getAccountUserById(idAccountUser, QUERY_GET_ACCOUNT_USER);
    }


    private AccountUser getAccountUserById(int id, String query) throws DAOException {
        AccountUser accountUser = null;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    accountUser = Creator.getInstance().createAccountUser(rs);
                }else{
                    throw new DAOException("SQLAccountUser Exception can't get accountUser " );
                }
                return accountUser;
            }
        }catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser " + e);
        }
    }
}
