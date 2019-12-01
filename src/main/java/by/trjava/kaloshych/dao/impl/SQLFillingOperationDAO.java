package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
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

public class SQLFillingOperationDAO  implements FillingOperationDAO {
    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static  final Logger logger=Logger.getLogger(SQLFillingOperationDAO.class);

    @Override
    public List<Component> getAllComponents() throws DAOException {
        DrinkDAO drinkDAO= DAOFactory.getInstance().getDrinkDAO();
        AdditionalIngredientDAO additionalIngredientDAO=DAOFactory.getInstance().getAdditionalIngredientDAO();

        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Component> componentList = new ArrayList<>();

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try{
            ps = con.prepareStatement(QUERY_FILLING_OPERATION);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idDrink = rs.getInt(PARAMETER_ID_DRINK);
                int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                if (idDrink != 0) {
                    componentList.add(drinkDAO.createDrink(idDrink));
                } else if (idAdditionalIngredient != 0) {
                    componentList.add(additionalIngredientDAO.createAdditionalIngredient(idAdditionalIngredient));
                }
            }
            return componentList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

        @Override
    public boolean fillingOperation(int idComponent) throws DAOException {
          DrinkDAO drinkDAO=DAOFactory.getInstance().getDrinkDAO();
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
        Connection con;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
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

    private boolean fillingDrinkComponent(int idComponent) throws DAOException {
        Connection con;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_FILLING_OPERATION_DRINK);
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
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    private boolean fillingAdditionalIngredient(int idComponent) throws DAOException {
        Connection con ;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
            ps = con.prepareStatement(QUERY_FILLING_OPERATION_ADDITIONAL_INGREDIENT);
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
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

}