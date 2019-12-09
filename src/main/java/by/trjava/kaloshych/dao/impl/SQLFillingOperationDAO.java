package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;

public class SQLFillingOperationDAO implements FillingOperationDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();


    @Override
    public List<Component> getAllComponents() throws DAOException {
        final AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();

        ResultSet rs = null;
        List<Component> componentList = new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_FILLING_OPERATION)) {
            rs = ps.executeQuery();
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
        } catch (SQLException e) {
            throw new DAOException("SQL Filling operation Exception can't get all components " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public void fillingOperation(int idComponent) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        if (drinkDAO.checkDrinkById(idComponent)) {
            fillingComponent(idComponent, QUERY_FILLING_MAX_PORTION_DRINK, QUERY_FILLING_DRINK);
        } else {
            fillingComponent(idComponent, QUERY_FILLING_ADDITIONAL, QUERY_FILLING_ADDITIONAL);
        }

    }

    @Override
    public void addComponentToFillingOperation(Component component) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        if (drinkDAO.checkDrinkById(component.getIdComponent())) {
            addComponentToFillingOperationTable(component, QUERY_DRINK_ADD_FILLING);
        } else {
            addComponentToFillingOperationTable(component, QUERY_ADDITIONAL_INGREDIENT_ADD_FILLING);
        }

    }

    private void addComponentToFillingOperationTable(Component component, String query) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, component.getIdComponent());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Filling operation Exception can't add component to filling table " + e);
        }
    }

    private void fillingComponent(int idComponent, String queryGetMaxPortion, String queryFilling) throws DAOException {
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper()) {

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
                ps2.executeUpdate();
                con.commit();
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