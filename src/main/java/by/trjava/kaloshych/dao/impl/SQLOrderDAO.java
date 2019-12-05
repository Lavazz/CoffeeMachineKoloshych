package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLOrderDAO implements OrderDAO {
    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public Order addOrder(CartUser cartUser, double totalCost) throws DAOException {
        ResultSet rs = null;
        int idOrder = 0;
        java.sql.Date dateOrder;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ORDER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cartUser.getIdCartUser());
            ps.setDouble(2, totalCost);
            dateOrder = new java.sql.Date(new java.util.Date().getTime());
            ps.setDate(3, dateOrder);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idOrder = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return new Order(idOrder, cartUser, dateOrder, totalCost);
        } catch (SQLException e) {
            throw new DAOException(e);

        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public boolean checkIdOrder(int idOrder) throws DAOException {
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CHECK_ID_ORDER)) {
            ps.setInt(1, idOrder);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
        return false;
    }


    private double getPriceDrink(int idDrink) throws DAOException {
        ResultSet rs = null;
        double drinkPrice = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_PRICE)) {
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();
            if (rs.next()) {
                drinkPrice = rs.getDouble(PARAMETER_PRICE);
            }
            return drinkPrice;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }


    @Override
    public Date getDateOrderByIdCartUser(CartUser cartUser) throws DAOException {
        Date dateOrder = null;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_DATE_ORDER)) {
            ps.setInt(1, cartUser.getIdCartUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                dateOrder = rs.getDate(PARAMETER_DATE_ORDER);
            }
            return dateOrder;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public double getTotalCostByIdCartUser(CartUser cartUser) throws DAOException {
        double totalCost = 0;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_TOTAL_COST)) {
            ps.setInt(1, cartUser.getIdCartUser());
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCost = rs.getDouble(PARAMETER_TOTAL_COST);
            }
            return totalCost;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }


    @Override
    public List<Order> getAllOrdersByUser(User user) throws DAOException {
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ORDERS_BY_USER)) {
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt(PARAMETER_ID_ORDER);
                int idCartUser = rs.getInt(PARAMETER_ID_CART_USER);
                Date dateOrder = rs.getDate(PARAMETER_DATE_ORDER);
                double totalCost = rs.getDouble(PARAMETER_TOTAL_COST);
                CartUser cartUser = new CartUser(idCartUser, user);
                orders.add(new Order(idOrder, cartUser, dateOrder, totalCost));
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

}






