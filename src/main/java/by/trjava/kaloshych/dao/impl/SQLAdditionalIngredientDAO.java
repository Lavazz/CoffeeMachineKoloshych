package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.AdditionalIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;

public class SQLAdditionalIngredientDAO implements AdditionalIngredientDAO {
    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public List<AdditionalIngredient> getAllAdditionalIngredients() throws DAOException {
        List<AdditionalIngredient> listAdditionalIngredient = new ArrayList<>();
        ResultSet rs = null;
        try {
            try (ProxyConnection proxyConnection = pool.getConnection();
                 Connection con = proxyConnection.getConnectionWrapper();
                 PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    listAdditionalIngredient.add(SQLUtil.getInstance().createAdditionalIngredient(rs));
                }
            }
                return listAdditionalIngredient;
            } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't get all ingredients "+e);
            } finally {
                JDBCShutter.shut(rs);
            }
        }


    @Override
    public AdditionalIngredient addNewAdditionalIngredient(String nameAdditionalIngredient, int calories) throws DAOException {
        int idAdditionalIngredient = 0;
        ResultSet rs = null;
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_ADD, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nameAdditionalIngredient);
            ps.setInt(2, calories);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idAdditionalIngredient = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return getAdditionalIngredient(idAdditionalIngredient);
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't add new ingredients "+e);
        } finally {
            JDBCShutter.shut(rs);
        }

    }

    @Override
    public boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException {

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_DELETE)) {
            ps.setInt(1, idAdditionalIngredient);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't delete ingredient "+e);
        }
    }

    @Override
    public AdditionalIngredient getAdditionalIngredient(int idAdditionalIngredient) throws DAOException {
        ResultSet rs = null;
       AdditionalIngredient additionalIngredient = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_BY_ID)) {
            ps.setInt(1, idAdditionalIngredient);
            rs = ps.executeQuery();
            while (rs.next()) {
             additionalIngredient=SQLUtil.getInstance().createAdditionalIngredient(rs);
            }
            return additionalIngredient;
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't create ingredient "+e);
        } finally {
            JDBCShutter.shut(rs);
        }

    }

    @Override
    public int decreasePortion(AdditionalIngredient additionalIngredient, int portion) throws DAOException {
        int newPortion = 0;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
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
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't decrease portion ingredient "+e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }

}
