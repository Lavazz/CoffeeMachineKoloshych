package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.dao.util.JDBCShutter;
import by.trjava.kaloshych.entity.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

/**
 * Represents methods for operation with Drink Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Drink
 * @since JDK1.0
 */
public class SQLDrinkDAO implements DrinkDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Drink> getAllDrinks() throws DAOException {
        List<Drink> drinkList = new ArrayList<>();

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ALL_DRINKS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    drinkList.add(Creator.getInstance().createDrink(rs));
                }
                return drinkList;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't get all drinks " + e);
        }
    }

    @Override
    public int decreasePortion(int idDrink, int portion) throws DAOException {
        int newPortion = 0;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try (Connection con = connectionPool.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID)) {
                ps.setInt(1, idDrink);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int oldPortion = rs.getInt(PARAMETER_PORTION);
                    newPortion = oldPortion - portion;
                }
                ps2 = con.prepareStatement(QUERY_DRINK_DECREASE_PORTION);
                ps2.setInt(1, newPortion);
                ps2.setInt(2, idDrink);
                ps2.executeUpdate();
                con.commit();
                return newPortion;
            } catch (SQLException e) {
                con.rollback();
                throw new DAOException("SQL Drink Exception can't decrease portion of drink " + e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't decrease portion of drink " + e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }

    @Override
    public double getDrinkPrice(int idDrink) throws DAOException {
        double price = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_PRICE)) {
            ps.setInt(1, idDrink);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    price = rs.getDouble(PARAMETER_PRICE);
                }
                return price;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't get drink price " + e);
        }

    }

    @Override
    public int getPortion(int idDrink) throws DAOException {
        int portion = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_PORTION)) {
            ps.setInt(1, idDrink);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    portion = rs.getInt(PARAMETER_PORTION);
                }
                return portion;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't get drink portion" + e);
        }
    }

    @Override
    public int addNewDrink(String drink, double price, String description) throws DAOException {
        int idDrink = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_ADD_NEW, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, drink);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idDrink = rs.getInt(PARAMETER_COLUMN_INDEX);
                }
                return idDrink;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't add new drink " + e);
        }
    }

    @Override
    public void deleteDrink(String drinkName) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_DELETE)) {
            ps.setString(1, drinkName);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't delete drink " + e);
        }
    }


    @Override
    public boolean checkDrinkById(int idComponent) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID)) {
            ps.setInt(1, idComponent);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.last()) {
                    System.out.println("true");
                    return true;
                } else {
                    System.out.println("false");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't check drink by id" + e);
        }
    }

    @Override
    public boolean isExistsDrink(String drinkName) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CHECK_DRINK)) {
            ps.setString(1, drinkName);
            try (ResultSet rs = ps.executeQuery()) {
               if(rs.next()){
                   return true;
               }else{
                   return false;
               }
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't check drink exists" + e);
        }
    }

    @Override
    public Drink getDrink(int idDrink) throws DAOException {
        Drink drink = null;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID)) {
            ps.setInt(1, idDrink);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    drink = Creator.getInstance().createDrink(rs);
                }
                return drink;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Drink Exception can't get drink " + e);
        }
    }

}

