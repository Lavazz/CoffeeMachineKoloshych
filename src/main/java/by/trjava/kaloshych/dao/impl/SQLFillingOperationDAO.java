package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.JDBCShutter;
import by.trjava.kaloshych.entity.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

/**
 * Represents methods for operation with FillingOperation Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see by.trjava.kaloshych.entity.FillingOperation
 * @since JDK1.0
 */
public class SQLFillingOperationDAO implements FillingOperationDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void fillingOperation(int idComponent) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        if (drinkDAO.checkDrinkById(idComponent)) {
            fillingComponent(idComponent, QUERY_GET_MAX_PORTION_DRINK, QUERY_FILLING_DRINK);
        } else {
            fillingComponent(idComponent, QUERY_GET_MAX_PORTION_ADDITIONAL_INGREDIENT, QUERY_FILLING_ADDITIONAL);
        }

    }

    @Override
    public void addComponentToFillingOperation(int idComponent) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        if (drinkDAO.checkDrinkById(idComponent)) {
            addComponentToFillingOperationTable(idComponent, QUERY_DRINK_ADD_FILLING);
        } else {
            addComponentToFillingOperationTable(idComponent, QUERY_ADDITIONAL_INGREDIENT_ADD_FILLING);
        }

    }


    @Override
    public List<Component> getAllComponents() throws DAOException {
        final DAOFactory daoFactory = DAOFactory.getInstance();
        final AdditionalIngredientDAO additionalIngredientDAO = daoFactory.getAdditionalIngredientDAO();
        final DrinkDAO drinkDAO = daoFactory.getDrinkDAO();

        List<Component> componentList = new ArrayList<>();

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_FILLING_OPERATION)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idDrink = rs.getInt(PARAMETER_ID_DRINK);
                    int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                    if (idDrink != 0) {
                        componentList.add(drinkDAO.getDrink(idDrink));
                    } else if (idAdditionalIngredient != 0) {
                        componentList.add(additionalIngredientDAO.getAdditionalIngredient(idAdditionalIngredient));
                    }
                }
                return componentList;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Filling operation Exception can't get all components " + e);
        }
    }


    private void addComponentToFillingOperationTable(int idComponent, String query) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idComponent);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Filling operation Exception can't add component to filling table " + e);
        }
    }

    private void fillingComponent(int idComponent, String queryGetMaxPortion, String queryFilling) throws DAOException {
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = PARAMETER_DEFALT_MAX_PORTION;

        try (Connection con = connectionPool.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(queryGetMaxPortion)) {
                ps.setInt(1, idComponent);
                rs = ps.executeQuery();
                if (rs.next()) {
                    maxPortion = rs.getInt(PARAMETER_MAX_PORTION);
                }
                ps2 = con.prepareStatement(queryFilling);
                ps2.setInt(1, maxPortion);
                ps2.setInt(2, idComponent);
                con.commit();
                ps2.executeUpdate();

            } catch (SQLException e) {
                con.rollback();
                throw new DAOException("SQL Filling operation Exception can't filling component " + e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Filling operation Exception can't filling component " + e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }

}