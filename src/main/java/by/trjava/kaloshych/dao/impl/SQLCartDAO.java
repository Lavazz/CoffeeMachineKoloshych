package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLCartDAO implements CartDAO {
    private  final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public Cart addDrinkToCart(CartUser cartUser, Drink drink, int portion) throws DAOException {
        ResultSet rs = null;
        int idCart = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
            PreparedStatement ps = con.prepareStatement(QUERY_CART_ADD, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cartUser.getIdCartUser());
            ps.setInt(2, drink.getIdComponent());
            ps.setInt(3, portion);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idCart = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return new Cart(idCart, cartUser, drink, portion);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public void deleteDrinkFromCart(Cart cart) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_DELETE)) {
            ps.setInt(1, cart.getIdCart());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean changePortion(Cart cart, int newPortion) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_CHANGE_PORTION)) {
            ps.setInt(1, newPortion);
            ps.setInt(2, cart.getIdCart());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Cart> getAllCarts(CartUser cartUser) throws DAOException {
        List<Cart> cartList = new ArrayList<>();
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ALL_CARTS_BY_CART_USER)) {
            ps.setInt(1, cartUser.getIdCartUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                cartList.add(SQLUtil.getInstance().createCart(rs));
            }
            return cartList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }


    @Override
    public int getPortionByCart(Cart cart) throws DAOException {
        int portion = 0;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_PORTION_BY_CART)) {
            ps.setInt(1, cart.getIdCart());
            rs = ps.executeQuery();
            while (rs.next()) {
                portion = rs.getInt(PARAMETER_PORTION);
            }
            return portion;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public Cart getCartById(int idCart) throws DAOException {
        ResultSet rs = null;
        Cart cart = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_CART_BY_ID)) {
            ps.setInt(1, idCart);
            rs = ps.executeQuery();
            while (rs.next()) {
                cart = SQLUtil.getInstance().createCart(rs);
            }
            return cart;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public List<Cart> getAllCartsByUser(User user) throws DAOException {
        List<Cart> carts = new ArrayList<>();
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ALL_CARTS_BY_USER)) {
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                carts.add(SQLUtil.getInstance().createCart(rs));
            }
            return carts;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

}


