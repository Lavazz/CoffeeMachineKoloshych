package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import by.trjava.kaloshych.entity.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLDrinkDAO implements DrinkDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public double getDrinkPrice(String drinkId) throws DAOException {
        double price = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_DRINK_GET_PRICE);
            ps.setString(1, drinkId);
            rs = ps.executeQuery();

            if (rs.next()) {
                price = Double.parseDouble(rs.toString());
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return price;
    }

    @Override
    public List<Drink> listAllDrink() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Drink> drinkList = new ArrayList<>();
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ALL_DRINKS);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(PARAMETER_ID_DRINK);
                String drink = rs.getString(PARAMETER_DRINK);
                double price = rs.getDouble(PARAMETER_PRICE);
                drinkList.add(new Drink(id, drink, price));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return drinkList;
    }

    //addDrink   for admin
    //deleteDrink for admin
    //updatePrice  for admin
}
