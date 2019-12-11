package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.PaymentMethodDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.util.Creator;
import by.trjava.kaloshych.dao.util.JDBCShutter;
import by.trjava.kaloshych.entity.PaymentMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.configuration.ConfigurationManager.*;
import static by.trjava.kaloshych.dao.configuration.SQLQuery.QUERY_ALL_PAYMENT_METHOD;
import static by.trjava.kaloshych.dao.configuration.SQLQuery.QUERY_GET_PAYMENT_METHOD;

/**
 * Represents methods for operation with PaymentMethods Entity in DAO.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see PaymentMethod
 * @since JDK1.0
 */
public class SQLPaymentMethodDAO implements PaymentMethodDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public PaymentMethod getPaymentMethod(int idPaymentMethod) throws DAOException {
        return receivePaymentMethod(idPaymentMethod, QUERY_GET_PAYMENT_METHOD);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() throws DAOException {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        try (Connection con = connectionPool.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(QUERY_ALL_PAYMENT_METHOD)) {
            while (rs.next()) {
                int idPaymentMethod = rs.getInt(PARAMETER_ID_PAYMENT_METHOD);
                if (idPaymentMethod == PAYMENT_METHOD_REGISTER
                        || idPaymentMethod == PAYMENT_METHOD_REMOVAL) {
                    continue;
                }
                paymentMethods.add(Creator.getInstance().createPaymentMethod(rs));
            }
            return paymentMethods;
        } catch (SQLException e) {
            throw new DAOException("SQL Payment method Exception can't get All payment methods " + e);
        }
    }

    private PaymentMethod receivePaymentMethod(int id, String query) throws DAOException {
        PaymentMethod paymentMethod = null;
        ResultSet rs = null;
        try (Connection con = connectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                paymentMethod = Creator.getInstance().createPaymentMethod(rs);
            }
            return paymentMethod;
        } catch (SQLException e) {
            throw new DAOException("SQLAccountUser Exception can't get accountUser " + e);
        } finally {
            JDBCShutter.shut(rs);
        }
    }

}

