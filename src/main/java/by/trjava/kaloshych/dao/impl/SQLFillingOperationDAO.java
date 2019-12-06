package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
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
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        final AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();

        ResultSet rs = null;
        List<Component> componentList = new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
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
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public boolean fillingOperation(int idComponent) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        boolean result;

        if (drinkDAO.checkDrinkById(idComponent)) {
            result = fillingDrinkComponent(idComponent);
        } else {
            result = fillingAdditionalIngredient(idComponent);
        }
        return result;
    }

    @Override
    public void addComponentToFillingOperation(Drink drink) throws DAOException {
        addComponentToFillingOperationTable(drink, QUERY_DRINK_ADD_FILLING);
    }

    @Override
    public void addComponentToFillingOperation(AdditionalIngredient additionalIngredient) throws DAOException {
        addComponentToFillingOperationTable(additionalIngredient, QUERY_ADDITIONAL_INGREDIENT_ADD_FILLING);
    }


    private void addComponentToFillingOperationTable(Component component, String query) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, component.getIdComponent());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private boolean fillingDrinkComponent(int idComponent) throws DAOException {
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_FILLING_OPERATION_DRINK)) {
            ps.setInt(1, idComponent);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxPortion = rs.getInt(PARAMETER_MAX_PORTION);
            }
            ps2 = con.prepareStatement(QUERY_FILLING_DRINK);
            ps2.setInt(1, maxPortion);
            ps2.setInt(2, idComponent);
            return ps2.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }

    private boolean fillingAdditionalIngredient(int idComponent) throws DAOException {
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_FILLING_OPERATION_ADDITIONAL_INGREDIENT)) {
            ps.setInt(1, idComponent);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxPortion = rs.getInt(PARAMETER_MAX_PORTION);
            }

            ps2 = con.prepareStatement(QUERY_FILLING_ADDITIONAL);
            ps2.setInt(1, maxPortion);
            ps2.setInt(2, idComponent);
            return ps2.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }

}