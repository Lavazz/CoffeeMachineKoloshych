package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.DrinkDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import by.trjava.coffeemachine.entity.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.coffeemachine.dao.impl.SQLQuery.*;

public class SQLDrinkDAO  implements DrinkDAO {

    private static final ConnectionPool pool=ConnectionPool.getInstance();
    @Override
   public double getDrinkPrice (String drinkId) throws DAOException {
        double price=0;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            con=pool.getConnection();
            ps=con.prepareStatement(QUERY_DRINK_GET_PRICE);
            ps.setString(1, drinkId);
            rs= ps.executeQuery();

            if (rs.next()){
                price = Double.parseDouble(rs.toString());
            }
        }catch (SQLException e){
            throw new DAOException("Exception in DrinkDAO");
        }finally {
           SQLUtil.shut(rs, ps, con);
        }
        return price;

    }

    @Override
    public List<Drink> listAllDrink() throws DAOException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
List<Drink> drinkList=new ArrayList<>();
        try{
            con=pool.getConnection();
ps=con.prepareStatement(QUERY_ALL_DRINKS);
           rs= ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id_drink");
                String drink = rs.getString("drink");
                double price = rs.getDouble("price");
                drinkList.add(new Drink(id, drink, price));
            }
        }catch (SQLException e){
            throw new DAOException("Exception in DrinkDAO");
        }finally {
           SQLUtil.shut(rs, ps, con);
        }
        return drinkList;
    }
}
