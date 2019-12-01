package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLOrderDAO implements OrderDAO {
    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLCartUserDAO.class);

    @Override
    public Order addOrder(CartUser cartUser, double totalCost) throws DAOException {
        Connection con ;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idOrder = 0;
        java.sql.Date dateOrder ;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_ORDER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
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
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public boolean checkIdOrder(int idOrder) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_CHECK_ID_ORDER);
            ps.setInt(1, idOrder);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new DAOException( e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return false;
    }


    private double getPriceDrink(int idDrink) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double drinkPrice = 0;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_DRINK_GET_PRICE);
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();
            if (rs.next()) {
                drinkPrice = rs.getDouble(PARAMETER_PRICE);
            }
            return drinkPrice;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }


    @Override
    public Date getDateOrderByIdCartUser(CartUser cartUser) throws DAOException {
        Date dateOrder = null;
        Connection con ;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_DATE_ORDER);
            ps.setInt(1, cartUser.getIdCartUser());
            rs = ps.executeQuery();
           while (rs.next()) {
                dateOrder = rs.getDate(PARAMETER_DATE_ORDER);
            }
            return dateOrder;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public double getTotalCostByIdCartUser(CartUser cartUser) throws DAOException {
        double totalCost = 0;
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_TOTAL_COST);
            ps.setInt(1, cartUser.getIdCartUser());
            rs = ps.executeQuery();
            if (rs.next()) {
                totalCost = rs.getDouble(PARAMETER_TOTAL_COST);
            }
            return totalCost;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

//    @Override
//    public List<Order> getAllOrdersByUser(User user) throws DAOException {
//        Connection con;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<Order> orders=new ArrayList<>();
//
//        try {
//            con = pool.getConnection();
//        } catch (ConnectionPoolException e) {
//            throw new DAOException("Exception in Connection Pool", e);
//        }
//        try {
//            ps = con.prepareStatement(QUERY_GET_CART_USER);
//            ps.setInt(1, user.getId());
//            rs=ps.executeQuery();
//            while (rs.next()){
//                int idCartUser=rs.getInt(PARAMETER_ID_CART_USER);
//               orders.add(getOrder(idCartUser, user));
//            }
//            return orders;
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            SQLUtil.shut(rs, ps, con);
//        }
//    }
//
//
//    private Order getOrder(int idCartUser, User user) throws DAOException {
//        Connection con;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//      Order order=null;
//
//        try {
//            con = pool.getConnection();
//        } catch (ConnectionPoolException e) {
//            throw new DAOException("Exception in Connection Pool", e);
//        }
//        try {
//            ps = con.prepareStatement(QUERY_GET_ORDER_BY_CART_USER);
//            ps.setInt(1, idCartUser);
//            rs=ps.executeQuery();
//            while (rs.next()){
//                int idOrder=rs.getInt(PARAMETER_ID_ORDER);
//                Date dateOrder=rs.getDate(PARAMETER_DATE_ORDER);
//                double totalCost=rs.getDouble(PARAMETER_TOTAL_COST);
//                CartUser cartUser=new CartUser(idCartUser, user);
//                order=new Order(idOrder, cartUser, dateOrder, totalCost);
//            }
//            return order;
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            SQLUtil.shut(rs, ps, con);
//        }
//    }


    @Override
    public List<Order> getAllOrdersByUser(User user) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orders=new ArrayList<>();

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_ORDERS_BY_USER);
      ps.setInt(1, user.getId());
      rs=ps.executeQuery();
      while (rs.next()){
          int idOrder=rs.getInt(PARAMETER_ID_ORDER);
          int idCartUser=rs.getInt(PARAMETER_ID_CART_USER);
          Date dateOrder=rs.getDate(PARAMETER_DATE_ORDER);
          double totalCost=rs.getDouble(PARAMETER_TOTAL_COST);
          CartUser cartUser=new CartUser(idCartUser, user);
          orders.add(new Order(idOrder, cartUser, dateOrder, totalCost));
      }
            return orders;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

//    public void cleanOrder(Order order){
//        Connection con;
//
//        try {
//            con = pool.getConnection();
//        } catch (ConnectionPoolException e) {
//            throw new DAOException("Exception in Connection Pool", e);
//        }
//        try (PreparedStatement  ps = con.prepareStatement(QUERY_ORDER_CLEAN)){
//            ps.setString(1, order.getIdOrder());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            try {
//                DBConnectionPool.getInstance().releaseConnection(con);
//            } catch (ConnectionPoolException e) {
//                logger.debug("Can't close connection pool" + e);
//            }
//        }
//    }
    }






