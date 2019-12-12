package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.entity.Account;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.QUERY_ACCOUNT_ADD;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.QUERY_GET_BALANCE;

/**
 * Represents methods for operation with Account Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Account
 * @since JDK1.0
 */
public class SQLAccountDAO implements AccountDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addNewAccount(int idAccountUser) throws DAOException {
        addAccount(idAccountUser, PAYMENT_METHOD_REGISTER, REGISTRATION_BONUS);
    }

    @Override
    public void replenishBalance(int idAccountUser, int idPaymentMethod, double amountOfMoney) throws DAOException {
        final AccountUserDAO accountUserDAO = DAOFactory.getInstance().getAccountUserDAO();
        AccountUser accountUser = accountUserDAO.getAccountUser(idAccountUser);
        double amountCurrent = getBalance(accountUser.getIdAccountUser());
        addAccount(idAccountUser, idPaymentMethod, (amountOfMoney + amountCurrent));
    }

    @Override
    public double decreaseBalance(Order order, int idAccountUser) throws DAOException {
        double amountCurrent = getBalance(idAccountUser);
        double amountNew = amountCurrent - order.getTotalCost();
        addAccount(idAccountUser, PAYMENT_METHOD_REMOVAL, amountNew);
        return amountNew;
    }

    @Override
    public double getBalance(int idAccountUser) throws DAOException {
        double balance = 0;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_BALANCE)) {
            ps.setInt(1, idAccountUser);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.last()) {
                    balance = rs.getDouble(PARAMETER_MONEY);
                    return balance;
                }else{
                    throw new DAOException("Balance is not founded");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("SQLAccount Exception can't get balance ", e);
        }
    }


    private void addAccount(int idAccountUser, int idPaymentMethod, double amountOfMoney) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ACCOUNT_ADD)) {
            ps.setInt(1, idAccountUser);
            ps.setInt(2, idPaymentMethod);
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            ps.setDate(3, date);
            ps.setDouble(4, amountOfMoney);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQLAccount Exception can't add account ", e);
        }
    }

}

