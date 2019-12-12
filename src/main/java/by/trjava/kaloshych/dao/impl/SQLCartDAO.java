package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.entity.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_PORTION;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

/**
 * Represents methods for operation with Cart Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Cart
 * @since JDK1.0
 */
public class SQLCartDAO implements CartDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int addDrinkToCart(int idCartUser, int idDrink, int portion) throws DAOException {
        int idCart = 0;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADD, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idCartUser);
            ps.setInt(2, idDrink);
            ps.setInt(3, portion);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    idCart = rs.getInt(PARAMETER_COLUMN_INDEX);
                }
                return idCart;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL cart Exception can't add drink to cart " + e);
        }
    }

    @Override
    public List<Cart> getAllCarts(int idCartUser) throws DAOException {
        return getCartList(idCartUser, QUERY_ALL_CARTS_BY_CART_USER);
    }

    @Override
    public List<Cart> getAllCartsByUser(int idUser) throws DAOException {
        return getCartList(idUser, QUERY_ALL_CARTS_BY_USER);
    }


    @Override
    public void deleteDrinkFromCart(int idCart) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_DELETE)) {
            ps.setInt(1, idCart);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL cart Exception can't delete drink from cart " + e);
        }
    }

    @Override
    public boolean changePortion(int idCart, int newPortion) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_CHANGE_PORTION)) {
            ps.setInt(1, newPortion);
            ps.setInt(2, idCart);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("SQL cart Exception can't change portion  drink to cart " + e);
        }
    }


    @Override
    public int getPortionByCart(Cart cart) throws DAOException {
        int portion = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_PORTION_BY_CART)) {
            ps.setInt(1, cart.getIdCart());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    portion = rs.getInt(PARAMETER_PORTION);
                }
                return portion;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL cart Exception can't get portion by cart " + e);
        }
    }

    @Override
    public Cart getCartById(int idCart) throws DAOException {
        Cart cart = null;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_CART_BY_ID)) {
            ps.setInt(1, idCart);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cart = Creator.getInstance().createCart(rs);
                }
                return cart;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL cart Exception can't get cart by id " + e);
        }
    }


    private List<Cart> getCartList(int id, String query) throws DAOException {
        List<Cart> carts = new ArrayList<>();

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    carts.add(Creator.getInstance().createCart(rs));
                }
                return carts;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL cart Exception can't get all carts by cartUser" + e);
        }
    }

}