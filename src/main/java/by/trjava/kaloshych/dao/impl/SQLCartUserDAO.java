package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLCartUserDAO implements CartUserDAO {

    private static final DBConnectionPool pool = DBConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(SQLCartUserDAO.class);

    @Override
    public CartUser addCartUser(User user) throws DAOException {
        int idCartUser = 0;
        ResultSet rs = null;

        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idCartUser = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return new CartUser(idCartUser, user);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }


    @Override
    public void deleteCartUser(User user) throws DAOException {
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_DELETE)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }


    @Override
    public List<CartUser> getCartUser(User user) throws DAOException {
        List<CartUser> CartUserList = new ArrayList<>();
        ResultSet rs = null;

        try ( Connection con = pool.getConnection();
              PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_BY_ID_USER)){
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                int idCartUser = rs.getInt(PARAMETER_ID_CART_USER);
                CartUserList.add(new CartUser(idCartUser, user));
            }
            return CartUserList;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public CartUser getCartUserById(int idCartUser) throws DAOException {
      final   UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        CartUser cartUser = null;
        ResultSet rs = null;

        try ( Connection con = pool.getConnection();
              PreparedStatement ps = con.prepareStatement(QUERY_CART_USER_BY_ID)){
            ps.setInt(1, idCartUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt(PARAMETER_ID_USER);
                User user = userDAO.getUserById(idUser);
                cartUser = new CartUser(idCartUser, user);
            }
            return cartUser;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception in Connection Pool", e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs);
        }
    }

}
