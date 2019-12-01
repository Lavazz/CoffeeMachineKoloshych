package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLDrinkDAO implements DrinkDAO {

    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLCartUserDAO.class);

    @Override
    public List<Drink> getAllDrinks() throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Drink> drinkList = new ArrayList<>();

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_ALL_DRINKS);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idDrink = rs.getInt(PARAMETER_ID_DRINK);
                drinkList.add(createDrink(idDrink));
            }
            return drinkList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public int decreasePortion(Drink drink, int portion) throws DAOException {
        int newPortion = 0;
        Connection con;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID);
            ps.setInt(1, drink.getIdComponent());
            rs = ps.executeQuery();
            while (rs.next()) {
                int oldPortion = rs.getInt(PARAMETER_PORTION);
                newPortion = oldPortion - portion;
            }
            ps2 = con.prepareStatement(QUERY_DRINK_DECREASE_PORTION);
            ps2.setInt(1, newPortion);
            ps2.setInt(2, drink.getIdComponent());
            ps2.executeUpdate();
            return  newPortion;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    @Override
    public double getDrinkPrice(int idDrink) throws DAOException {
        double price = 0;
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_DRINK_GET_PRICE);
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();

            while (rs.next()) {
                price = rs.getDouble(PARAMETER_PRICE);
            }
            return price;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }

    }

    @Override
    public Drink addNewDrink(String drink, double price, String description) throws DAOException {
        int idDrink = 0;
        Connection con;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_DRINK_ADD_NEW, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, drink);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idDrink = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
//            ps2 = con.prepareStatement(QUERY_DRINK_ADD_FILLING);
//            ps2.setInt(1, idDrink);
//            ps2.executeUpdate();
            return createDrink(idDrink);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    @Override
    public void deleteDrink(String drinkName) throws DAOException {
        Connection con;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try (PreparedStatement  ps = con.prepareStatement(QUERY_DRINK_DELETE)){
            ps.setString(1, drinkName);
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
    public boolean changePrice(String drink, int newPrice) throws DAOException {
        Connection con;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try (PreparedStatement ps = con.prepareStatement(QUERY_DRINK_CHANGE_PRICE)){
            ps.setInt(1, newPrice);
            ps.setString(2, drink);
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
    public boolean checkDrinkById(int idComponent) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result=false;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID);
            ps.setInt(1, idComponent);
            rs = ps.executeQuery();
            while (rs.next()) {
                result= true;
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public Drink createDrink(int idDrink) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Drink drink = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_DRINK);
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nameDrink = rs.getString(PARAMETER_DRINK);
                String description = rs.getString(PARAMETER_DESCRIPTION);
                String picturePath = rs.getString(PARAMETER_PICTURE_PATH);
                int portion = rs.getInt(PARAMETER_PORTION);
                double price = rs.getDouble(PARAMETER_PRICE);
                drink = new Drink(idDrink, nameDrink, portion, picturePath, description, price);
            }
            return drink;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }

    }


    @Override
    public Drink getDrink(Cart cart) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Drink drink = null;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_GET_DRINK_BY_CART);
            ps.setInt(1, cart.getIdCart());
            rs = ps.executeQuery();
            while (rs.next()) {
                int idDrink = rs.getInt(PARAMETER_ID_DRINK);
                drink = createDrink(idDrink);
            }
            return drink;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }
}

