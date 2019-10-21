package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.FillingOperationDAO;
import by.trjava.coffeemachine.dao.SQLDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import by.trjava.coffeemachine.entity.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.trjava.coffeemachine.dao.impl.SQLQuery.QUERY_FILLING_OPERATION;

public class SQLFillingOperationDAO extends SQLDAO implements FillingOperationDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

//    @Override
//    public int checkСompleteness() throws DAOException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try    {
//            con = pool.getConnection();
//            ps = con.prepareStatement();
//
//        }catch ( SQLException e){
//            throw new DAOException("Exception in FillingOperationDAO");
//        }finally {
//            SQLCloser.close(rs, ps, con);
//        }
//        return 0;
//    }

    @Override
    public boolean fillingOperation(Ingredient ingredient, int amountPortion) throws DAOException {
        boolean result;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_FILLING_OPERATION);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Exception in FillingOperationDAO");
        } finally {
           SQLUtil.shut(con, ps);
        }

        return result;
    }
}