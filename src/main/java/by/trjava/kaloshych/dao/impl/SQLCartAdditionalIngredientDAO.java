package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartAdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLCartAdditionalIngredientDAO implements CartAdditionalIngredientDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public void addAdditionalIngredientToCartAI(Cart cart, AdditionalIngredient additionalIngredient) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADDITIONAL_INGREDIENT_ADD)) {
            ps.setInt(1, cart.getIdCart());
            ps.setInt(2, additionalIngredient.getIdComponent());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't add cartAdditionalIngredient " + e);
        }
    }

    @Override
    public void deleteAdditionalIngredientFromCartAI(CartAdditionalIngredient cartAdditionalIngredient) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADDITIONAL_INGREDIENT_DELETE)) {
            ps.setInt(1, cartAdditionalIngredient.getIdCartAdditionalIngredient());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't delete cartAdditionalIngredient " + e);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredientByCart(Cart cart) throws DAOException {
        List<CartAdditionalIngredient> cartAdditionalIngredientsList = new ArrayList<>();
        ResultSet rs = null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ALL_INGREDIENTS)) {
            ps.setInt(1, cart.getIdCart());
            rs = ps.executeQuery();
            while (rs.next()) {
                cartAdditionalIngredientsList.add(SQLUtil.getInstance().createCartAdditionalIngredient(rs));
            }
            return cartAdditionalIngredientsList;
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't get All cartAdditionalIngredients " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws DAOException {
        List<CartAdditionalIngredient> cartAdditionalIngredientsList = new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(QUERY_ALL_INGREDIENTS)) {
            while (rs.next()) {
                cartAdditionalIngredientsList.add(SQLUtil.getInstance().createCartAdditionalIngredient(rs));
            }
            return cartAdditionalIngredientsList;
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't get All cartAdditionalIngredients " + e);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(User user) throws DAOException {
        List<CartAdditionalIngredient> cartAdditionalIngredients = new ArrayList<>();
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_INGREDIENTS_BY_USER)) {
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                cartAdditionalIngredients.add(SQLUtil.getInstance().createCartAdditionalIngredient(rs));
            }
            return cartAdditionalIngredients;
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't get cartAdditionalIngredient " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }


    @Override
    public CartAdditionalIngredient getCartAdditionalIngredientsById(int idCartAdditionalIngredient) throws DAOException {
        CartAdditionalIngredient cartAdditionalIngredient = null;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_INGREDIENTS_BY_ID)) {
            ps.setInt(1, idCartAdditionalIngredient);
            rs = ps.executeQuery();
            while (rs.next()) {
                cartAdditionalIngredient = SQLUtil.getInstance().createCartAdditionalIngredient(rs);
            }
            return cartAdditionalIngredient;
        } catch (SQLException e) {
            throw new DAOException("SQL cartAdditionalIngredient Exception can't get cartAdditionalIngredient " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

}
