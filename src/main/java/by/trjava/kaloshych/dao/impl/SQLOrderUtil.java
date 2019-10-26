package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.PARAMETER_PRICE;

public class SQLOrderUtil {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static double getPriceDrink(int idDrink) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double drinkPrice = 0;
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_DRINK_GET_PRICE);
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();
            if (rs.next()) {

                drinkPrice = rs.getDouble(PARAMETER_PRICE);
            }
            return drinkPrice;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    private static double getPriceAdditionalIngredient(int additionalIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double additionalIngredientPrice = 0;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_GET_ADDITIONAL_INGREDIENT);
            ps.setInt(1, additionalIngredient);
            rs = ps.executeQuery();
            if (rs.next()) {
                additionalIngredientPrice = rs.getDouble(PARAMETER_PRICE);
            }
            return additionalIngredientPrice;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    public static double calculateTotalPrice(int idDrink, int idAdditionalIngredient, int portion) throws DAOException {
        double drinkPrice = getPriceDrink(idDrink);
        double additionalIngredientPrice = getPriceAdditionalIngredient(idAdditionalIngredient);
        return drinkPrice * (portion + additionalIngredientPrice);
    }

}
