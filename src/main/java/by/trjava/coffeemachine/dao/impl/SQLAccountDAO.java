package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.AccountDAO;
import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import by.trjava.coffeemachine.entity.Account;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.entity.Order;

import java.sql.*;

import static by.trjava.coffeemachine.dao.impl.SQLQuery.*;

public class SQLAccountDAO implements AccountDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public double increaseBalance(int idUser, String paymentMethod, double amountOfMoney) throws DAOException, SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2=null;
        ResultSet rs = null;
        double amountOld=0, amountNew = 0;

        try {

con=pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_ACCOUNT_CHECK_BALANCE);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if ( rs.last()) {
                amountOld = rs.getDouble("AmountOfMoney");
            }
            amountNew = amountOfMoney + amountOld;
            ps2 = con.prepareStatement(QUERY_ACCOUNT_RECHARGE);
            if(setQuery(ps2, idUser, paymentMethod, amountNew)){
                con.commit();
                return amountNew;
            }else {
                throw new DAOException("Account is not recharged");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Exception in AccountDAO");
        } finally {
           SQLUtil.shut(rs, ps, ps2, con);
        }

    }

    @Override
    public double decreaseBalance(OrderJournal orderJournal, double totalCost) throws DAOException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        double amountOld = 0, amountNew = 0;
        int idUser=0;

        try {
            idUser= DAOFactory.getInstance().getUserDAO().getIdUserByOrder(orderJournal);
            con=pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_ACCOUNT_CHECK_BALANCE);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if ( rs.last()) {
                amountOld = rs.getDouble("AmountOfMoney");
            }
amountNew=amountOld-totalCost;
            if(amountNew<0){
                throw new DAOException("There's not enough money in the account");
            }

            ps2 = con.prepareStatement(QUERY_ACCOUNT_DECREASE);

            ps2.setDouble(1, amountNew);
            ps2.setInt(2, idUser);
            ps2.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Exception in AccountDAO");
        } finally {
           SQLUtil.shut(rs, ps, ps2, con);
        }
        return amountNew;

    }

    private boolean setQuery(PreparedStatement ps, int idUser, String paymentMethod, double amountOfMoney) throws SQLException {
        ps.setInt(1, idUser);
        ps.setString(2, paymentMethod);
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        ps.setTimestamp(3, date);
        ps.setDouble(4, amountOfMoney);
        return ps.executeUpdate() > 0;
    }


    //    @Override
//    public double createAccount(String login, String paymentMethod, double amountOfMoney) throws DAOException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//PreparedStatement ps2=null;
//ResultSet rs=null;
//        int idUser=0;
//
//        try {
//            con = pool.getConnection();
//ps2=con.prepareStatement(QUERY_GET_ID);
//ps2.setString(1, login);
//rs=ps2.executeQuery();
//if(rs.next()){
//    idUser=rs.getInt("id_user");
//    System.out.println(" idUser=Integer.parseInt(rs.toString());");
//}else{
//}
//            ps = con.prepareStatement(QUERY_CREATE_ACCOUNT);
//         if(setQuery(ps, idUser, paymentMethod,amountOfMoney)){
//             return amountOfMoney;
//         }else {
//             throw new DAOException("Account is not created");
//         }
//        } catch (SQLException e) {
//            throw new DAOException("Exception in AccountDAO");
//        } finally {
//           SQLUtil.shut(con, ps);
//        }
//    }

}
