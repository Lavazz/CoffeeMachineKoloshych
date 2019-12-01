package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.ComponentDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.CLASS_DRINK;

public class SQLComponentDAO implements ComponentDAO {

    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLComponentDAO.class);

        @Override
    public void deleteComponent(Component component, String type) throws DAOException {
        Connection con;
 String query;
        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }

        if(type.equals(CLASS_DRINK)){
          query= QUERY_DRINK_DELETE;
        }else{
            query=QUERY_ADDITIONAL_INGREDIENT_DELETE;
        }
        try (PreparedStatement ps = con.prepareStatement( query)) {

            ps.setInt(1, component.getIdComponent());
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
    public void deleteComponentFromFillingOperation(Component component, String type) throws DAOException {
        Connection con;
String query;
        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        if(type.equals(CLASS_DRINK)){
            query= QUERY_DELETE_DRINK_FROM_FILLING_OPERATION;
        }else{
            query=QUERY_DELETE_ADDITIONAL_INGREDIENT_FROM_FILLING_OPERATION;
        }
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, component.getIdComponent());
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
}
