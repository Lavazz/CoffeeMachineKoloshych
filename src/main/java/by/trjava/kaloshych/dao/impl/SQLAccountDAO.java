package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;

import java.sql.*;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;


public class SQLAccountDAO implements AccountDAO {

    private final ConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public void addNewAccount(AccountUser accountUser) throws DAOException {
        addAccount(accountUser, PAYMENT_METHOD_REGISTER, REGISTRATION_BONUS);
    }

    @Override
    public void replenishBalance(AccountUser accountUser, int idPaymentMethod, double amountOfMoney) throws DAOException {
        double amountCurrent = getBalance(accountUser.getUser());
        addAccount(accountUser, idPaymentMethod, (amountOfMoney + amountCurrent));
    }

    @Override
    public double decreaseBalance(Order order) throws DAOException {
        AccountUserDAO accountUserDAO = DAOFactory.getInstance().getAccountUserDAO();
        AccountUser accountUser = accountUserDAO.getAccountUser(order.getCartUser().getUser());

        double amountCurrent = getBalance(accountUser.getUser());
        double amountNew = amountCurrent - order.getTotalCost();
        addAccount(accountUser, PAYMENT_METHOD_REMOVAL, amountNew);
        return amountNew;
    }

    @Override
    public double getBalance(User user) throws DAOException {
        double balance = 0;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_BALANCE)) {
            ps.setInt(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.last()) {
                    balance = rs.getDouble(PARAMETER_MONEY);
                }
                return balance;
            }
        } catch (SQLException e) {
            throw new DAOException("SQLAccount Exception can't get balance ", e);
        }
    }


    private void addAccount(AccountUser accountUser, int idPaymentMethod, double amountOfMoney) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ACCOUNT_ADD)) {
            ps.setInt(1, accountUser.getIdAccountUser());
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

