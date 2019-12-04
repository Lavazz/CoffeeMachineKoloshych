package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartAdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLCartAdditionalIngredientDAO implements CartAdditionalIngredientDAO {

    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLCartAdditionalIngredientDAO.class);

    @Override
    public boolean addAdditionalIngredientToCartAI(Cart cart, AdditionalIngredient additionalIngredient) throws DAOException {
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADDITIONAL_INGREDIENT_ADD)) {
            ps.setInt(1, cart.getIdCart());
            ps.setInt(2, additionalIngredient.getIdComponent());

            return ps.executeUpdate() > 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteAdditionalIngredientFromCartAI(CartAdditionalIngredient cartAdditionalIngredient) throws DAOException {
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_ADDITIONAL_INGREDIENT_DELETE)) {
            ps.setInt(1, cartAdditionalIngredient.getIdCartAdditionalIngredient());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredientByCart(Cart cart) throws DAOException {
        final AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
        List<CartAdditionalIngredient> cartAdditionalIngredientsList = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_GET_ALL_INGREDIENTS)) {
            ps.setInt(1, cart.getIdCart());
            rs = ps.executeQuery();
            while (rs.next()) {
                int idCartAdditionalIngredient = rs.getInt(PARAMETER_ID_CART_ADDITIONAL_INGREDIENT);
                int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                AdditionalIngredient additionalIngredient = additionalIngredientDAO.createAdditionalIngredient(idAdditionalIngredient);
                cartAdditionalIngredientsList.add(new CartAdditionalIngredient(idCartAdditionalIngredient, cart, additionalIngredient));
            }
            return cartAdditionalIngredientsList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws DAOException {
        final CartDAO cartDAO = DAOFactory.getInstance().getCartDAO();
        final AdditionalIngredientDAO additionalIngredientDAO = new SQLAdditionalIngredientDAO();
        List<CartAdditionalIngredient> cartAdditionalIngredientsList = new ArrayList<>();

        ResultSet rs = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ALL_INGREDIENTS)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int idCartAdditionalIngredient = rs.getInt(PARAMETER_ID_CART_ADDITIONAL_INGREDIENT);
                int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                int idCart = rs.getInt(PARAMETER_ID_CART);
                Cart cart = cartDAO.createCartById(idCart);
                AdditionalIngredient additionalIngredient = additionalIngredientDAO.createAdditionalIngredient(idAdditionalIngredient);
                cartAdditionalIngredientsList.add(new CartAdditionalIngredient(idCartAdditionalIngredient, cart, additionalIngredient));
            }
            return cartAdditionalIngredientsList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);

        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(User user) throws DAOException {
        final CartDAO cartDAO = DAOFactory.getInstance().getCartDAO();
        final AdditionalIngredientDAO additionalIngredientDAO = new SQLAdditionalIngredientDAO();
        List<CartAdditionalIngredient> cartAdditionalIngredients = new ArrayList<>();

        ResultSet rs = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_INGREDIENTS_BY_USER)) {
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                int idCartAdditionalIngredient = rs.getInt(PARAMETER_ID_CART_ADDITIONAL_INGREDIENT);
                int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                int idCart = rs.getInt(PARAMETER_ID_CART);
                Cart cart = cartDAO.createCartById(idCart);
                AdditionalIngredient additionalIngredient = additionalIngredientDAO.createAdditionalIngredient(idAdditionalIngredient);
                cartAdditionalIngredients.add(new CartAdditionalIngredient(idCartAdditionalIngredient, cart, additionalIngredient));
            }
            return cartAdditionalIngredients;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);

        } finally {
            SQLUtil.shut(rs);
        }
    }

}
