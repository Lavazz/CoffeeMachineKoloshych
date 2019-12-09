package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.ComponentDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.connection.ConnectionWrapper;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.Component;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.CLASS_DRINK;

public class SQLComponentDAO implements ComponentDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public void deleteComponent(Component component, String type) throws DAOException {
        String query;
        if (type.equals(CLASS_DRINK)) {
            query = QUERY_DRINK_DELETE;
        } else {
            query = QUERY_ADDITIONAL_INGREDIENT_DELETE;
        }

        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, component.getIdComponent());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Component Exception can't delete component " + e);
        }
    }

    @Override
    public void deleteComponentFromFillingOperation(Component component, String type) throws DAOException {
        String query;
        if (type.equals(CLASS_DRINK)) {
            query = QUERY_DELETE_DRINK_FROM_FILLING_OPERATION;
        } else {
            query = QUERY_DELETE_ADDITIONAL_INGREDIENT_FROM_FILLING_OPERATION;
        }
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, component.getIdComponent());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Component Exception can't delete component from filling operation " + e);
        }
    }
}
