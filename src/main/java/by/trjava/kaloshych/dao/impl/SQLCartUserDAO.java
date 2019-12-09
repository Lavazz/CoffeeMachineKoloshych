package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLCartUserDAO implements CartUserDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public int addCartUser(User user) throws DAOException {
        int idCartUser = 0;
        ResultSet rs = null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idCartUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return idCartUser;
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't add cartUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }


    @Override
    public void deleteCartUser(User user) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_DELETE)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't delete cartUser " + e);
        }
    }


    @Override
    public List<CartUser> getCartUser(User user) throws DAOException {
        List<CartUser> cartUserList = new ArrayList<>();
        ResultSet rs=null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_BY_ID_USER)) {
            ps.setInt(1, user.getId());
            rs= ps.executeQuery();
            while (rs.next()) {
                cartUserList.add(SQLUtil.getInstance().createCartUser(rs));
            }
            return cartUserList;
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't get list cartUser " + e);
        }
    }

    @Override
    public CartUser getLastCartUser(User user) throws DAOException {
        CartUser cartUser = null;
        ResultSet rs=null;
        System.out.println("in last cartUser dao");
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_BY_ID_USER)) {
            ps.setInt(1, user.getId());
            rs= ps.executeQuery();
            if (rs.last()) {
              cartUser =  SQLUtil.getInstance().createCartUser(rs);
            }
            return cartUser;
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't get last cartUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }


    @Override
    public CartUser getCartUserById(int idCartUser) throws DAOException {

        CartUser cartUser = null;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_BY_ID)) {
            ps.setInt(1, idCartUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                cartUser = SQLUtil.getInstance().createCartUser(rs);
            }
            return cartUser;
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't add cartUser by id " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

}
