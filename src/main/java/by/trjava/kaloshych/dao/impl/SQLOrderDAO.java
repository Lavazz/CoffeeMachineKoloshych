package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.OrderDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;

import java.sql.*;

import static by.trjava.kaloshych.dao.impl.SQLQuery.*;

public class SQLOrderDAO implements OrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int addOrder(int idUser) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idOrder = 0;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ORDER_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idUser);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idOrder = rs.getInt(1);
            }
            return idOrder;
        } catch (SQLException e) {
            throw new DAOException(e);

        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }


//    @Override
//    public void deleteOrder(int idOrder) throws DAOException {
//        Connection con=null;
//        PreparedStatement ps=null;
//
//               try {
//            con = pool.getConnection();
//            ps = con.prepareStatement(QUERY_DELETE_ORDER);
//            ps.setInt(1, idOrder);
//ps.executeUpdate();
//        }catch (SQLException  e){
//            throw new DAOException("Exception in OrderDAO");
//        }finally {
//                  SQLUtil.shut(con, ps);
//        }
//    }

    public boolean checkIdOrder(int idOder) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_CHECK_ID_ORDER);
            ps.setInt(1, idOder);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return false;
    }

//    @Override
//    public List<Order> getUserOrder(int idUser) {
//        Connection con=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        List<Order> orderList=new ArrayList<>();
//
//        try{
//            con=pool.getConnection();
//            ps=con.prepareStatement(QUERY_ALL_ORDERS);
//            ps.setInt(1, idUser);
//            rs=ps.executeQuery();
//            if(rs.next()){
//              orderList.add(makeOrder(rs));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }  finally {
//           SQLUtil.shut(rs, ps, con);
//        }
//        return orderList;
//    }
//
//
//    private Order makeOrder(ResultSet rs) throws SQLException {
//        Order order=null;
//        order.setIdOrder(rs.getInt("id_order"));
//        order.setIdUser(rs.getInt("id_user"));
//        order.setIdDrink(rs.getInt("id_drink"));
//        order.setIdAdditionalIngredient(rs.getInt("id_additionalIngredient"));
//        order.setPortion(rs.getInt("id_portion"));
//       return order;
//    }

    //    public List<Order> getOrderHistory(int idUser, int drink, int additionalIngredient, int portion) throws DAOException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        PreparedStatement ps2 = null;
//        PreparedStatement ps3 = null;
//        ResultSet rs = null;
//        OrderJournalDAO orderJournalDAO=DAOFactory.getInstance().getOrderJournalDAO();
//        List<OrderJournal> listOrderJournal=orderJournalDAO.getUserOrderHistory(idUser);
//        for(OrderJournal orderJournal:listOrderJournal){
//            if(orderJournal.getIdOrder()==)
//        }
//        try {
//            con = pool.getConnection();
//            con.setAutoCommit(false);
//
//            ps=con.prepareStatement(QUERY_CREATE_ORDER);
//            ps.setInt(1, idUser);
//            int idOrder=ps2.executeUpdate();
//
//            ps3=con.prepareStatement(QUERY_CREATE_ORDER_BASKET);
//            ps2.setInt(1, idOrder);
//            ps.setInt(2, drink);
//            ps.setInt(3, additionalIngredient);
//            ps.setInt(4, portion);
//            ps3.executeUpdate();
//
//double totalCost=SQLOrderUtil.calculateTotalPrice(drink, additionalIngredient, portion);
//            AccountDAO accountDAO =DAOFactory.getInstance().getAccountDAO();
//            Order order=new Order(idUser, drink, additionalIngredient, portion, totalCost);
//          double balance= accountDAO.decreaseBalance(order);
////нужно добавить, чтобы отнимало количество порций
//            con.commit();
//            return balance;
//
//                 }catch (SQLException  e){
//            throw new DAOException("Exception in OrderDAO");
//        }finally {
//           SQLUtil.shut(rs, ps, ps2, con);
//        }
//    }
//
}

