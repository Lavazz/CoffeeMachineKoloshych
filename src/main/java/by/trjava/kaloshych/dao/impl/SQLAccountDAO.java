package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import by.trjava.kaloshych.entity.OrderJournal;

import java.sql.*;

import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.PARAMETER_MONEY;
import static by.trjava.kaloshych.dao.impl.SQLQuery.*;

public class SQLAccountDAO implements AccountDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public double increaseBalance(int idUser, String paymentMethod, double amountOfMoney) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        double amountOld = 0, amountNew = 0;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ACCOUNT_CHECK_BALANCE);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if (rs.last()) {
                amountOld = rs.getDouble(PARAMETER_MONEY);
            }
            amountNew = amountOfMoney + amountOld;
            ps2 = con.prepareStatement(QUERY_ACCOUNT_RECHARGE);
            if (setQuery(ps2, idUser, paymentMethod, amountNew)) {
                return amountNew;
            } else {
                throw new DAOException("Exception in SQL");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    @Override
    public double decreaseBalance(OrderJournal orderJournal, double totalCost) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        double amountOld = 0, amountNew = 0;
        int idUser = 0;

        try {
            idUser = DAOFactory.getInstance().getUserDAO().getIdUserByOrder(orderJournal);
            con = pool.getConnection();
            ps = con.prepareStatement(QUERY_ACCOUNT_CHECK_BALANCE);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if (rs.last()) {
                amountOld = rs.getDouble(PARAMETER_MONEY);
            }
            amountNew = amountOld - totalCost;
            if (amountNew < 0) {
                throw new DAOException("There's not enough money in the account ");
            }

            ps2 = con.prepareStatement(QUERY_ACCOUNT_DECREASE);
            ps2.setDouble(1, amountNew);
            ps2.setInt(2, idUser);
            ps2.executeUpdate();
            return amountNew;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            SQLUtil.shut(rs, ps, ps2, con);
        }
    }

    private boolean setQuery(PreparedStatement ps, int idUser, String paymentMethod, double amountOfMoney) throws SQLException {
        ps.setInt(1, idUser);
        ps.setString(2, paymentMethod);
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        ps.setTimestamp(3, date);
        ps.setDouble(4, amountOfMoney);
        return ps.executeUpdate() > 0;
    }

}
