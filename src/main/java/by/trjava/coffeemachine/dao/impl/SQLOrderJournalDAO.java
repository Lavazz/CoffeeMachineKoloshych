package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.AccountDAO;
import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.OrderJournalDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import by.trjava.coffeemachine.entity.OrderJournal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.coffeemachine.dao.impl.SQLQuery.QUERY_CREATE_ORDER_BASKET;
import static by.trjava.coffeemachine.dao.impl.SQLQuery.QUERY_ORDER_JOURNAL_LIST;

public class SQLOrderJournalDAO  implements OrderJournalDAO {

    private static final ConnectionPool pool=ConnectionPool.getInstance();

@Override
    public double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException, SQLException {
        int idOrder=0;
        int idDrink=0;
        int idAdditionalIngredient=0;
        int portion=0;
        double totalCost=0;

        for(OrderJournal order:listOrderJournal) {
            idOrder = order.getIdOrder();
            idDrink = order.getIdDrink();
            idAdditionalIngredient = order.getIdAdditionalIngredient();
            portion = order.getPortion();
            createOrderJournal(idOrder, idDrink, idAdditionalIngredient, portion);

            totalCost += SQLOrderUtil.calculateTotalPrice(idDrink, idAdditionalIngredient, portion);

            AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
            OrderJournal orderJournal = new OrderJournal(idOrder, idDrink, idAdditionalIngredient, portion);
           accountDAO.decreaseBalance(orderJournal, totalCost);
        }
//нужно добавить, чтобы отнимало количество порций
            return totalCost;
        }

    private void createOrderJournal(int idOrder, int idDrink, int idAdditionalIngredient, int portion) throws DAOException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
        con=pool.getConnection();
        ps=con.prepareStatement(QUERY_CREATE_ORDER_BASKET);
        ps.setInt(1,idOrder);
        ps.setInt(2, idDrink);
        ps.setInt(3, idAdditionalIngredient);
        ps.setInt(4, portion);
        ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        throw new DAOException("Exception in OrderJournalDAO");
        }finally {
       SQLUtil.shut(rs, ps, con);
        }
        }

    @Override
    public List<OrderJournal> getUserOrderHistory(String userLogin) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<OrderJournal> orderJournalList=new ArrayList<>();

        int  idUser=DAOFactory.getInstance().getUserDAO().getIdUserByLogin(userLogin);
        try{
            con=pool.getConnection();
            ps=con.prepareStatement(QUERY_ORDER_JOURNAL_LIST);
            ps.setInt(1, idUser);
            rs=ps.executeQuery();
            while(rs.next()){
                orderJournalList.add(makeOrderJournal(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
           SQLUtil.shut(rs, ps, con);
        }
        return orderJournalList;
    }


    private OrderJournal makeOrderJournal(ResultSet rs) throws SQLException {
        OrderJournal orderJournal=null;
        orderJournal.setIdOrder(rs.getInt("id_order"));
        orderJournal.setIdDrink(rs.getInt("id_drink"));
        orderJournal.setIdAdditionalIngredient(rs.getInt("id_additionalIngredient"));
        orderJournal.setPortion(rs.getInt("id_portion"));
        return orderJournal;
    }
}
