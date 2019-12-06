package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.util.JDBCShutter;
import by.trjava.kaloshych.dao.impl.util.SQLUtil;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLDrinkDAO implements DrinkDAO {

    private final DBConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public List<Drink> getAllDrinks() throws DAOException {
        ResultSet rs = null;
        List<Drink> drinkList = new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_ALL_DRINKS)){
            rs = ps.executeQuery();
            while (rs.next()) {
                drinkList.add(SQLUtil.getInstance().createDrink(rs));
            }
            return drinkList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public int decreasePortion(Drink drink, int portion) throws DAOException {
        int newPortion = 0;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID)){
            ps.setInt(1, drink.getIdComponent());
            rs = ps.executeQuery();
            while (rs.next()) {
                int oldPortion = rs.getInt(PARAMETER_PORTION);
                newPortion = oldPortion - portion;
            }
            ps2 = con.prepareStatement(QUERY_DRINK_DECREASE_PORTION);
            ps2.setInt(1, newPortion);
            ps2.setInt(2, drink.getIdComponent());
            ps2.executeUpdate();
            return  newPortion;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs, ps2);
        }
    }

    @Override
    public double getDrinkPrice(int idDrink) throws DAOException {
        double price = 0;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_PRICE)){
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();

            while (rs.next()) {
                price = rs.getDouble(PARAMETER_PRICE);
            }
            return price;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }

    }

    @Override
    public int getPortion(Drink drink) throws DAOException {
        int portion=0;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_PORTION)){
            ps.setInt(1, drink.getIdComponent());
            rs = ps.executeQuery();

            while (rs.next()) {
               portion = rs.getInt(PARAMETER_PORTION);
            }
            return portion;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }

    }

    @Override
    public Drink addNewDrink(String drink, double price, String description) throws DAOException {
        int idDrink = 0;
        ResultSet rs = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_ADD_NEW, PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setString(1, drink);
            ps.setDouble(2, price);
            ps.setString(3, description);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idDrink = rs.getInt(PARAMETER_COLUMN_INDEX);
            }
            return getDrink(idDrink);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public void deleteDrink(String drinkName) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_DELETE)){
            ps.setString(1, drinkName);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean changePrice(String drink, int newPrice) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_CHANGE_PRICE)){
            ps.setInt(1, newPrice);
            ps.setString(2, drink);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean checkDrinkById(int idComponent) throws DAOException {
        ResultSet rs = null;
        boolean result=false;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID)){
            ps.setInt(1, idComponent);
            rs = ps.executeQuery();
            while (rs.next()) {
                result= true;
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

    @Override
    public Drink getDrink(int idDrink) throws DAOException {
        ResultSet rs = null;
        Drink drink = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_DRINK_GET_BY_ID)){
            ps.setInt(1, idDrink);
            rs = ps.executeQuery();
            while (rs.next()) {

                drink = SQLUtil.getInstance().createDrink(rs);
            }
            return drink;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }

    }


    @Override
    public Drink getDrink(Cart cart) throws DAOException {
        ResultSet rs = null;
        Drink drink = null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_GET_DRINK_BY_CART)){
            ps.setInt(1, cart.getIdCart());
            rs = ps.executeQuery();
            while (rs.next()) {
                drink = SQLUtil.getInstance().createDrink(rs);
            }
            return drink;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }
}

