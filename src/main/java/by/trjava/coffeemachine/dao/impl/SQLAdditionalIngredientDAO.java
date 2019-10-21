package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.AdditionalIngredientDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import by.trjava.coffeemachine.entity.AdditionalIngredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.coffeemachine.dao.impl.SQLQuery.QUERY_ADDITIONAL_INGREDIENT;

public class SQLAdditionalIngredientDAO implements AdditionalIngredientDAO {
    private static final ConnectionPool pool=ConnectionPool.getInstance();

    @Override
    public List<AdditionalIngredient> listAllAdditionalIngredient() throws DAOException {
        List<AdditionalIngredient> listAdditionalIngredient=new ArrayList<>();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=pool.getConnection();
ps=con.prepareStatement(QUERY_ADDITIONAL_INGREDIENT);
rs=ps.executeQuery();
while (rs.next()){
    listAdditionalIngredient.add(createAI(rs));
}
        }catch (SQLException e){
            throw new DAOException("Exception in AdditionalIngredientDAO");
        }finally {
           SQLUtil.shut(rs, ps, con);
        }
        return listAdditionalIngredient;
    }


    private AdditionalIngredient createAI(ResultSet rs){
        AdditionalIngredient additionalIngredient=new AdditionalIngredient();

        try {
            additionalIngredient.setIdAdditionalIngredient(rs.getInt("id_additionalIngredient"));
            additionalIngredient.setNameIngredient(rs.getString("additionalIngredient"));
            additionalIngredient.setBrand(rs.getString("Brand"));
            additionalIngredient.setPrice(rs.getDouble("Price"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NumberFormatException();
        }
        return additionalIngredient;
    }
}
