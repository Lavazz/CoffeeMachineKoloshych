package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.ComponentDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.entity.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.configuration.SQLQuery.*;


/**
 * Represents methods for operation with Component Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Component
 * @since JDK1.0
 */
public class SQLComponentDAO implements ComponentDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void deleteComponent(int idComponent) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        String query;
        if (drinkDAO.checkDrinkById(idComponent)) {
            query = QUERY_DRINK_DELETE;
        } else {
            query = QUERY_ADDITIONAL_INGREDIENT_DELETE;
        }
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idComponent);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Component Exception can't delete component " + e);
        }
    }

    @Override
    public void deleteComponentFromFillingOperation(int idComponent) throws DAOException {
        final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
        String query;
        if (drinkDAO.checkDrinkById(idComponent)) {
            query = QUERY_DELETE_DRINK_FROM_FILLING_OPERATION;
        } else {
            query = QUERY_DELETE_ADDITIONAL_INGREDIENT_FROM_FILLING_OPERATION;
        }
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idComponent);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Component Exception can't delete component from filling operation " + e);
        }
    }

}
