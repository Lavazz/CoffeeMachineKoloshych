package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLCartDAO implements CartDAO {
    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLCartDAO.class);

    @Override
    public Cart addDrinkToCart(CartUser cartUser, Drink drink, int portion) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idCart = 0;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_CART_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cartUser.getIdCartUser());
            ps.setInt(2, drink.getIdComponent());
            ps.setInt(3, portion);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idCart = rs.getInt(PARAMETER_COLUMN_INDEX);
            } return new Cart(idCart, cartUser, drink, portion);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public void deleteDrinkFromCart(Cart cart) throws DAOException {
        Connection con;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try (PreparedStatement ps = con.prepareStatement(QUERY_CART_DELETE)) {
            ps.setInt(1, cart.getIdCart());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                DBConnectionPool.getInstance().releaseConnection(con);
            } catch (ConnectionPoolException e) {
                logger.debug("Can't close connection pool" + e);
            }
        }
    }

    @Override
    public boolean changePortion (Cart cart, int newPortion) throws DAOException {
        Connection con;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try (PreparedStatement ps = con.prepareStatement(QUERY_CART_CHANGE_PORTION)){
            ps.setInt(1, newPortion);
            ps.setInt(2, cart.getIdCart());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                DBConnectionPool.getInstance().releaseConnection(con);
            } catch (ConnectionPoolException e) {
                logger.debug("Can't close connection pool" + e);
            }
        }
    }

    @Override
    public List<Cart> getAllCarts(CartUser cartUser) throws DAOException {
        List<Cart> cartList = new ArrayList<>();
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_ALL_CARTS_BY_CART_USER);
            ps.setInt(1, cartUser.getIdCartUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                cartList.add(makeCart(rs));
            }
            return cartList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }


    @Override
    public int getPortionByCart(Cart cart) throws DAOException {
        int portion = 0;
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_PORTION_BY_CART);
            ps.setInt(1, cart.getIdCart());
            rs = ps.executeQuery();
            while (rs.next()) {
                portion = rs.getInt(PARAMETER_PORTION);
            }
            return portion;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public Cart createCartById(int idCart) throws DAOException{
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
       Cart cart = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_CART_BY_ID);
            ps.setInt(1, idCart);
            rs = ps.executeQuery();
            while (rs.next()) {
            cart=makeCart(rs);
            }
            return cart;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public List<Cart> getAllCartsByUser(User user) throws DAOException {
        List<Cart> carts = new ArrayList<>();
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_ALL_CARTS_BY_USER);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                carts.add(makeCart(rs));
            }
            return carts;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    private Cart makeCart(ResultSet rs) throws SQLException, DAOException {
        CartUserDAO cartUserDAO=DAOFactory.getInstance().getCartUserDAO();
        DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();

        int idCart=rs.getInt(PARAMETER_ID_CART);
        int idCartUser = rs.getInt(PARAMETER_ID_CART_USER);
        CartUser cartUser=cartUserDAO.getCartUserById(idCartUser);
        int idDrink=rs.getInt(PARAMETER_ID_DRINK);
        Drink drink= drinkDAO.createDrink(idDrink);
        int portion=rs.getInt(PARAMETER_PORTION);
        return  new Cart(idCart, cartUser, drink, portion);
    }
    }


