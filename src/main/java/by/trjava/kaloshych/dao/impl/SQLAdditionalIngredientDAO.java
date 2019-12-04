package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;

public class SQLAdditionalIngredientDAO implements AdditionalIngredientDAO {
    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLAdditionalIngredientDAO.class);


    @Override
    public List<AdditionalIngredient> getAllAdditionalIngredients() throws DAOException {
        List<AdditionalIngredient> listAdditionalIngredient = new ArrayList<>();
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        }
        try {
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
    public AdditionalIngredient addNewAdditionalIngredient(String nameAdditionalIngredient, int calories) throws DAOException {
        int idAdditionalIngredient = 0;
        ResultSet rs = null;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_ADD, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nameAdditionalIngredient);
            ps.setInt(2, calories);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idAdditionalIngredient = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return createAdditionalIngredient(idAdditionalIngredient);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }

    }

    @Override
    public boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException {

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_DELETE)) {
            ps.setInt(1, idAdditionalIngredient);
            return ps.executeUpdate() > 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public AdditionalIngredient createAdditionalIngredient(int idAdditionalIngredient) throws DAOException {
        ResultSet rs = null;
        AdditionalIngredient component = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_BY_ID)) {
            ps.setInt(1, idAdditionalIngredient);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nameComponent = rs.getString(PARAMETER_ADDITIONAL_INGREDIENT);
                int calories = rs.getInt(PARAMETER_CALORIES);
                int portion = rs.getInt(PARAMETER_PORTION);
                String picturePath = rs.getString(PARAMETER_PICTURE_PATH);
                component = new AdditionalIngredient(idAdditionalIngredient, nameComponent, portion, picturePath, calories);
            }
            return component;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }

    }

    @Override
    public int decreasePortion(AdditionalIngredient additionalIngredient, int portion) throws DAOException {
        int newPortion = 0;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_BY_ID)) {
            ps.setInt(1, additionalIngredient.getIdComponent());
            rs = ps.executeQuery();
            if (rs.next()) {
                newPortion = rs.getInt(PARAMETER_PORTION) - portion;
            }
            ps2 = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_DECREASE_PORTION);
            ps2.setInt(1, newPortion);
            ps2.setInt(2, additionalIngredient.getIdComponent());
            ps2.executeUpdate();
            return newPortion;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps2);
        }
    }


    private AdditionalIngredient createAdditionalIngredient(ResultSet rs) throws DAOException {
        AdditionalIngredient additionalIngredient = new AdditionalIngredient();

        try {
            additionalIngredient.setIdComponent(rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT));
            additionalIngredient.setNameComponent(rs.getString(PARAMETER_ADDITIONAL_INGREDIENT));
            additionalIngredient.setCalories(rs.getInt(PARAMETER_CALORIES));
            additionalIngredient.setPicturePath(rs.getString(PARAMETER_PICTURE_PATH));
            additionalIngredient.setPortion(rs.getInt(PARAMETER_PORTION));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return additionalIngredient;
    }

}
