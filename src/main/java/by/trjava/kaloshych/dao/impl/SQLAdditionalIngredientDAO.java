package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.impl.SQLQuery.*;

public class SQLAdditionalIngredientDAO implements AdditionalIngredientDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<AdditionalIngredient> listAllAdditionalIngredient() throws DAOException {
        List<AdditionalIngredient> listAdditionalIngredient = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT);
            rs = ps.executeQuery();
            while (rs.next()) {
                listAdditionalIngredient.add(createAdditionalIngredient(rs));
            }
            return listAdditionalIngredient;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public boolean addAdditionalIngredient(String nameAdditionalIngredient, double price) throws DAOException {
        int id_additionalIngredient = 0;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_ADD, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nameAdditionalIngredient);
            ps.setDouble(2, price);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id_additionalIngredient = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            ps2 = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_ADD_FILLING);
            ps2.setInt(1, id_additionalIngredient);
            return ps2.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }

    }

    @Override
    public boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_DELETE);
            ps.setInt(1, idAdditionalIngredient);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(ps, con);
        }
    }

    private AdditionalIngredient createAdditionalIngredient(ResultSet rs) throws DAOException {
        AdditionalIngredient additionalIngredient = new AdditionalIngredient();

        try {
            additionalIngredient.setIdIngredient(rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT));
            additionalIngredient.setNameIngredient(rs.getString(PARAMETER_ADDITIONAL_INGREDIENT));
            additionalIngredient.setPortion(rs.getInt(PARAMETER_PORTION));
            additionalIngredient.setPrice(rs.getDouble(PARAMETER_PRICE));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return additionalIngredient;
    }
}
