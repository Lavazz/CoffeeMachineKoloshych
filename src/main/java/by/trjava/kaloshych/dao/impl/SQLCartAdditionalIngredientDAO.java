package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartAdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.dao.util.JDBCShutter;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.configuration.SQLQuery.*;

/**
 * Represents methods for operation with CartAdditionalIngredient Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see CartAdditionalIngredient
 * @since JDK1.0
 */
public class SQLCartAdditionalIngredientDAO implements CartAdditionalIngredientDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addAdditionalIngredientToCartAI(Cart cart, int idAdditionalIngredient) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADDITIONAL_INGREDIENT_ADD)) {
            ps.setInt(1, cart.getIdCart());
            ps.setInt(2, idAdditionalIngredient);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't add cartAdditionalIngredient " + e);
        }
    }

    @Override
    public void deleteAdditionalIngredientFromCartAI(int idCartAdditionalIngredient) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADDITIONAL_INGREDIENT_DELETE)) {
            ps.setInt(1, idCartAdditionalIngredient);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't delete cartAdditionalIngredient " + e);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredientByCart(Cart cart) throws DAOException {
        return getCartAdditionalList(cart.getIdCart(), QUERY_GET_ALL_INGREDIENTS);
    }


    @Override
    public List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(int idUser) throws DAOException {
        return getCartAdditionalList(idUser, QUERY_INGREDIENTS_BY_USER);
    }


    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws DAOException {
        return getCartAdditionalList(NOT_PARAMETERS, QUERY_ALL_INGREDIENTS);
    }


    @Override
    public CartAdditionalIngredient getCartAdditionalIngredientsById(int idCartAdditionalIngredient) throws DAOException {
        CartAdditionalIngredient cartAdditionalIngredient = null;
        ResultSet rs = null;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_INGREDIENTS_BY_ID)) {
            ps.setInt(1, idCartAdditionalIngredient);
            rs = ps.executeQuery();
            while (rs.next()) {
                cartAdditionalIngredient = Creator.getInstance().createCartAdditionalIngredient(rs);
            }
            return cartAdditionalIngredient;
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't get cartAdditionalIngredient " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }


    private List<CartAdditionalIngredient> getCartAdditionalList(int id, String query) throws DAOException {
        final DAOFactory daoFactory = DAOFactory.getInstance();
        final CartDAO cartDAO = daoFactory.getCartDAO();
        final AdditionalIngredientDAO additionalIngredientDAO = daoFactory.getAdditionalIngredientDAO();

        List<CartAdditionalIngredient> cartAdditionalIngredientsList = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            if (id != NOT_PARAMETERS) {
                ps.setInt(1, id);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int idCartAdditionalIngredient = rs.getInt(PARAMETER_ID_CART_ADDITIONAL_INGREDIENT);
                int idCart = rs.getInt(PARAMETER_ID_CART);
                Cart cart = cartDAO.getCartById(idCart);
                int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                AdditionalIngredient additionalIngredient = additionalIngredientDAO.getAdditionalIngredient(idAdditionalIngredient);

                cartAdditionalIngredientsList.add(Creator.getInstance().createCartAdditionalIngredient(rs));
            }
            return cartAdditionalIngredientsList;
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't get All cartAdditionalIngredients " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

}
