package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.dao.util.JDBCShutter;
import by.trjava.kaloshych.entity.AdditionalIngredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_PORTION;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

/**
 * Represents methods for operation with AdditionalIngredient Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see AdditionalIngredient
 * @since JDK1.0
 */
public class SQLAdditionalIngredientDAO implements AdditionalIngredientDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int addNewAdditionalIngredient(String nameAdditionalIngredient, int calories) throws DAOException {
        int idAdditionalIngredient = 0;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_ADD, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nameAdditionalIngredient);
            ps.setInt(2, calories);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    idAdditionalIngredient = rs.getInt(PARAMETER_COLUMN_INDEX);
                }
                return idAdditionalIngredient;
            }
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't add new ingredients " + e);
        }
    }


    @Override
    public AdditionalIngredient getAdditionalIngredient(int idAdditionalIngredient) throws DAOException {
        return getAdditionalIngredientById(idAdditionalIngredient, QUERY_ADDITIONAL_INGREDIENT_BY_ID);
    }

    @Override
    public List<AdditionalIngredient> getAllAdditionalIngredients() throws DAOException {
        List<AdditionalIngredient> listAdditionalIngredient = new ArrayList<>();
        try {
            try (Connection con = connectionPool.getConnection();
                 Statement statement = con.createStatement();
                 ResultSet rs = statement.executeQuery(QUERY_ADDITIONAL_INGREDIENT)) {
                while (rs.next()) {
                    listAdditionalIngredient.add(Creator.getInstance().createAdditionalIngredient(rs));
                }
            }
            return listAdditionalIngredient;
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't get all ingredients " + e);
        }
    }

    @Override
    public boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_DELETE)) {
            ps.setInt(1, idAdditionalIngredient);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't delete ingredient " + e);
        }
    }


    @Override
    public boolean isExistsAdditionalIngredient(String nameAdditionalIngredient) throws DAOException {
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CHECK_ADDITIONAL_INGREDIENT)) {
            ps.setString(1, nameAdditionalIngredient);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Additional ingredient Exception can't check  exists" + e);
        }
    }

    @Override
    public int decreasePortion(int idAdditionalIngredient, int portion) throws DAOException {
        int newPortion = 0;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try (Connection con = connectionPool.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_BY_ID)) {
                ps.setInt(1, idAdditionalIngredient);
                rs = ps.executeQuery();
                if (rs.next()) {
                    newPortion = rs.getInt(PARAMETER_PORTION) - portion;
                }else{
                    throw new DAOException("SQLAdditionalIngredient Exception can't decrease portion ingredient ");
                }
                ps2 = con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT_DECREASE_PORTION);
                ps2.setInt(1, newPortion);
                ps2.setInt(2, idAdditionalIngredient);
                ps2.executeUpdate();
                con.commit();
                return newPortion;
            } catch (SQLException e) {
                con.rollback();
                throw new DAOException("SQLAdditionalIngredient Exception can't decrease portion ingredient " + e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't decrease portion ingredient " + e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }


    private AdditionalIngredient getAdditionalIngredientById(int id, String query) throws DAOException {
        AdditionalIngredient additionalIngredient = null;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
              if (rs.next()) {
                    additionalIngredient = Creator.getInstance().createAdditionalIngredient(rs);
                }else{
                  throw new DAOException("SQLAdditionalIngredient Exception can't create ingredient " );
              }
                return additionalIngredient;
            }
        } catch (SQLException e) {
            throw new DAOException("SQLAdditionalIngredient Exception can't create ingredient " + e);
        }
    }

}
