package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.PaymentMethodDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import by.trjava.kaloshych.entity.PaymentMethod;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.QUERY_ALL_PAYMENT_METHOD;
import static by.trjava.kaloshych.dao.impl.configuration.SQLQuery.QUERY_GET_PAYMENT_METHOD;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.*;

public class SQLPaymentMethodDAO implements PaymentMethodDAO {
    private final ConnectionPool pool = DBConnectionPool.getInstance();

    @Override
    public PaymentMethod createPaymentMethod(int idPaymentMethod) throws DAOException {
        ResultSet rs = null;
        String namePaymentMethod=null;

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_GET_PAYMENT_METHOD)){
            ps.setInt(1, idPaymentMethod);
         rs= ps.executeQuery();
            while (rs.next()) {
            namePaymentMethod = rs.getString(PARAMETER_NAME_PAYMENT_METHOD);
            }
            return new PaymentMethod(idPaymentMethod, namePaymentMethod);
        } catch (SQLException e) {
            throw new DAOException(e);

        } finally {
            SQLUtil.shut(rs);
        }
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() throws DAOException {
        ResultSet rs = null;
       List<PaymentMethod> paymentMethods=new ArrayList<>();

        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
              PreparedStatement ps = con.prepareStatement(QUERY_ALL_PAYMENT_METHOD)){
            rs= ps.executeQuery();
            while (rs.next()) {
              int idPaymentMethod = rs.getInt(PARAMETER_ID_PAYMENT_METHOD);
              if(idPaymentMethod==1||idPaymentMethod==2){
                  continue;
              }
                String namePaymentMethod = rs.getString(PARAMETER_NAME_PAYMENT_METHOD);
                paymentMethods.add(new PaymentMethod(idPaymentMethod, namePaymentMethod));
            }
            return paymentMethods;
        } catch (SQLException e) {
            throw new DAOException(e);

        } finally {
            SQLUtil.shut(rs);
        }
    }

}

