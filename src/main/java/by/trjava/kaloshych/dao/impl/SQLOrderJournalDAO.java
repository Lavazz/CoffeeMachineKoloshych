package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.OrderJournalDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import by.trjava.kaloshych.entity.OrderJournal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.SQLQuery.QUERY_CREATE_ORDER_BASKET;
import static by.trjava.kaloshych.dao.impl.SQLQuery.QUERY_ORDER_JOURNAL_LIST;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLOrderJournalDAO implements OrderJournalDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException {
        int idOrder = 0;
        int idDrink = 0;
        int idAdditionalIngredient = 0;
        int portion = 0;
        double totalCost = 0;

        for (OrderJournal orderJournal : listOrderJournal) {
            idOrder = orderJournal.getIdOrder();
            idDrink = orderJournal.getIdDrink();
            idAdditionalIngredient = orderJournal.getIdAdditionalIngredient();
            portion = orderJournal.getPortion();
            createOrderJournal(idOrder, idDrink, idAdditionalIngredient, portion);

            totalCost += SQLOrderUtil.calculateTotalPrice(idDrink, idAdditionalIngredient, portion);

            AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();

            //     accountDAO.decreaseBalance(orderJournal, totalCost);
        }
//нужно добавить, чтобы отнимало количество порций
        return totalCost;
    }

    private void createOrderJournal(int idOrder, int idDrink, int idAdditionalIngredient, int portion) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_CREATE_ORDER_BASKET);
            ps.setInt(1, idOrder);
            ps.setInt(2, idDrink);
            ps.setInt(3, idAdditionalIngredient);
            System.out.println("idAdditionalIngredient" + idAdditionalIngredient);
            ps.setInt(4, portion);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in OrderJournalDAO");
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
    }

    @Override
    public List<OrderJournal> getUserOrderHistory(int idUser) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderJournal> orderJournalList = new ArrayList<>();

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ORDER_JOURNAL_LIST);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderJournalList.add(makeOrderJournal(rs));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, con);
        }
        return orderJournalList;
    }


    private OrderJournal makeOrderJournal(ResultSet rs) throws SQLException {
        OrderJournal orderJournal = null;
        orderJournal.setIdOrder(rs.getInt(PARAMETER_ID_ORDER));
        orderJournal.setIdDrink(rs.getInt(PARAMETER_ID_DRINK));
        orderJournal.setIdAdditionalIngredient(rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT));
        orderJournal.setPortion(rs.getInt(PARAMETER_PORTION));
        return orderJournal;
    }

    //delete
}
