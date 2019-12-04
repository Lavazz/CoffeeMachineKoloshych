package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.ComponentDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
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

    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLComponentDAO.class);

        @Override
    public void deleteComponent(Component component, String type) throws DAOException {
            String query;
            if(type.equals(CLASS_DRINK)){
                query= QUERY_DRINK_DELETE;
            }else{
                query=QUERY_ADDITIONAL_INGREDIENT_DELETE;
            }

            try ( Connection con = pool.getConnection();
                  PreparedStatement ps = con.prepareStatement( query)) {

            ps.setInt(1, component.getIdComponent());
            ps.executeUpdate();
            } catch (ConnectionPoolException e) {
                throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteComponentFromFillingOperation(Component component, String type) throws DAOException {
String query;
        if(type.equals(CLASS_DRINK)){
            query= QUERY_DELETE_DRINK_FROM_FILLING_OPERATION;
        }else{
            query=QUERY_DELETE_ADDITIONAL_INGREDIENT_FROM_FILLING_OPERATION;
        }
        try ( Connection con = pool.getConnection();
              PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, component.getIdComponent());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
