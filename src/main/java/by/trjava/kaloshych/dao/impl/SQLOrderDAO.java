package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.OrderDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.entity.Order;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;


/**
 * Represents methods for operation with Order Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Order
 * @since JDK1.0
 */
public class SQLOrderDAO implements OrderDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int addOrder(int idCartUser, double totalCost) throws DAOException {
        int idOrder;
        Date dateOrder;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ORDER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idCartUser);
            ps.setDouble(2, totalCost);
            dateOrder = new java.sql.Date(new java.util.Date().getTime());
            ps.setDate(3, dateOrder);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idOrder = rs.getInt(PARAMETER_COLUMN_INDEX);
                } else {
                    throw new DAOException("SQL Order Exception can't add order ");
                }
                return idOrder;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't add order " + e);
        }
    }


    @Override
    public Order getOrder(int idOrder) throws DAOException {
        Order order = null;
        CachedRowSet crs = getResultSetOrder(idOrder, QUERY_GET_ORDER);
        try {
            while (crs.next()) {
                order = Creator.getInstance().createOrder(crs);
            }
            return order;
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't get all orders by user " + e);
        }
    }

    @Override
    public Order getLastOrderByUser(int idUser) throws DAOException {
        Order order = null;
        CachedRowSet crs = getResultSetOrder(idUser, QUERY_GET_ORDERS_BY_USER);
        try {
            if (crs.last()) {
                order = Creator.getInstance().createOrder(crs);
            }
            return order;
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't get last order by user " + e);
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(int idUser) throws DAOException {
        List<Order> orders = new ArrayList<>();
        CachedRowSet crs = getResultSetOrder(idUser, QUERY_GET_ORDERS_BY_USER);
        try {
            while (crs.next()) {
                orders.add(Creator.getInstance().createOrder(crs));
            }
            return orders;
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't get all orders by user " + e);
        }

    }

    private CachedRowSet getResultSetOrder(int id, String query) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                RowSetFactory factory = RowSetProvider.newFactory();
                CachedRowSet cachedRowSet = factory.createCachedRowSet();
                cachedRowSet.populate(rs);
                return cachedRowSet;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Order Exception can't get all orders by user " + e);
        }
    }

}