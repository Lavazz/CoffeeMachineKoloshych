package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.SQLDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.BasicIngredient;
import by.trjava.kaloshych.entity.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLFillingOperationDAO extends SQLDAO implements FillingOperationDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<Ingredient> getIngredient() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ingredient> ingredientList = new ArrayList<>();

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_FILLING_OPERATION);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idBasicIngredient = rs.getInt(PARAMETER_ID_BASIC_INGREDIENT);
                int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
                if (idBasicIngredient != 0) {
                    ingredientList.add(createBasicIngredient(idBasicIngredient));
                } else if (idAdditionalIngredient != 0) {
                    ingredientList.add(createAdditionalIngredient(idAdditionalIngredient));
                }
            }
            return ingredientList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    private Ingredient createBasicIngredient(int idBasicIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ingredient ingredient = null;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_BASIC_INGREDIENT);
            ps.setInt(1, idBasicIngredient);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nameIngredient = rs.getString(PARAMETER_BASIC_INGREDIENT);
                String type = rs.getString(PARAMETER_TYPE);
                String brand = rs.getString(PARAMETER_BRAND);
                int portion = rs.getInt(PARAMETER_PORTION);
                ingredient = new BasicIngredient(idBasicIngredient, nameIngredient, type, brand, portion);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return ingredient;
    }

    private Ingredient createAdditionalIngredient(int idAdditionalIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ingredient ingredient = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_BY_ID);
            ps.setInt(1, idAdditionalIngredient);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nameIngredient = rs.getString(PARAMETER_ADDITIONAL_INGREDIENT);
                double price = rs.getDouble(PARAMETER_PRICE);
                int portion = rs.getInt(PARAMETER_PORTION);
                ingredient = new AdditionalIngredient(idAdditionalIngredient, nameIngredient, price, portion);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return ingredient;
    }

    @Override
    public int fillingOperation(int idIngredient) throws DAOException {
        boolean result = false;
        int portion = 0;

        if (checkBasicByID(idIngredient)) {
            result = fillingBasicIngredient(idIngredient);
        } else {
            result = fillingAdditionalIngredient(idIngredient);
        }
        if (result) {
            portion = getPortion(idIngredient);
        }
        return portion;
    }

    private boolean fillingBasicIngredient(int idIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_FILLING_OPERATION_BASIC_INGREDIENT);
            ps.setInt(1, idIngredient);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxPortion = rs.getInt(PARAMETER_MAX_PORTION);
            }
            ps2 = con.prepareStatement(QUERY_FILLING_BASIC);
            ps2.setInt(1, maxPortion);
            ps2.setInt(2, idIngredient);
            return ps2.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    private boolean fillingAdditionalIngredient(int idIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int maxPortion = 0;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_FILLING_OPERATION_ADDITIONAL_INGREDIENT);
            ps.setInt(1, idIngredient);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxPortion = rs.getInt(PARAMETER_MAX_PORTION);
            }

            ps2 = con.prepareStatement(QUERY_FILLING_ADDITIONAL);
            ps2.setInt(1, maxPortion);
            ps2.setInt(2, idIngredient);
            return ps2.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    private int getPortion(int idIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int portion = 0;
        try {
            con = pool.getConnection();
            if (checkBasicByID(idIngredient)) {
                ps = con.prepareStatement(QUERY_FILLING_GET_BASIC);
            } else {
                ps = con.prepareStatement(QUERY_FILLING_GET_ADDITIONAL_PORTION);
            }
            ps.setInt(1, idIngredient);
            rs = ps.executeQuery();
            if (rs.next()) {
                portion = rs.getInt(PARAMETER_PORTION);
            }
            return portion;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }

    }

    private boolean checkBasicByID(int idIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_FILLING_GET_BASIC);
            ps.setInt(1, idIngredient);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

}