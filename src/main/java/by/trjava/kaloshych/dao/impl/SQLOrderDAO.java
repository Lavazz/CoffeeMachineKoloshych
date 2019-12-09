package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLOrderDAO implements OrderDAO {
    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public int addOrder(CartUser cartUser, double totalCost) throws DAOException {
        ResultSet rs = null;
        int idOrder = 0;
        java.sql.Date dateOrder;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
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
            return idOrder;
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't add order " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public boolean checkIdOrder(int idOrder) throws DAOException {
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ORDER)) {
            ps.setInt(1, idOrder);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't check order " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
        return false;
    }

    @Override
    public Order getOrder(int idOrder) throws DAOException {
        ResultSet rs = null;
        Order order = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ORDER)) {
            ps.setInt(1, idOrder);
            rs = ps.executeQuery();
            while (rs.next()) {
                order=SQLUtil.getInstance().createOrder(rs);
            }
            return order;
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't get all orders by user " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public Order getLastOrderByUser(User user) throws DAOException {
        Order order = null;
        ResultSet rs =null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ORDERS_BY_USER)) {
            ps.setInt(1, user.getId());
            rs= ps.executeQuery();
            if (rs.last()) {
                System.out.println("in if last order ");
                order=SQLUtil.getInstance().createOrder(rs);
            }
            return order;
        } catch(SQLException e){
            throw new DAOException("SQL Order Exception can't get last order by user " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(User user) throws DAOException {
        List<Order> orders = new ArrayList<>();
        ResultSet rs ;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ORDERS_BY_USER)) {
            ps.setInt(1, user.getId());
            rs= ps.executeQuery();
                while (rs.next()) {
                    orders.add(SQLUtil.getInstance().createOrder(rs));
                }
                return orders;
            } catch(SQLException e){
                throw new DAOException("SQL Order Exception can't get all orders by user " + e);

        }
    }

}