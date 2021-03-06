package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.entity.CartUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.PARAMETER_COLUMN_INDEX;
import static by.trjava.kaloshych.dao.util.configuration.SQLQuery.*;

/**
 * Represents methods for operation with CartUser Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see CartUser
 * @since JDK1.0
 */
public class SQLCartUserDAO implements CartUserDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public int addCartUser(int idUser) throws DAOException {
        int idCartUser = 0;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUser);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    idCartUser = rs.getInt(PARAMETER_COLUMN_INDEX);
                }
                return idCartUser;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't add cartUser " + e);
        }
    }

    @Override
    public CartUser getLastCartUser(int idUser) throws DAOException {
        return getCartUserById(idUser, QUERY_CART_USER_BY_ID_USER);
    }

    @Override
    public CartUser getCartUser(int idCartUser) throws DAOException {
        return getCartUserById(idCartUser, QUERY_CART_USER_BY_ID);
    }


    private CartUser getCartUserById(int id, String query) throws DAOException {
        CartUser cartUser = null;

        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.last()) {
                    cartUser = Creator.getInstance().createCartUser(rs);
                }
                return cartUser;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL CartUser Exception can't get cartUser by id " + e);
        }
    }
}

