package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.PaymentMethod;

import java.util.List;

public interface PaymentMethodDAO {


    PaymentMethod getPaymentMethod(int idPaymentMethod) throws DAOException;

    List<PaymentMethod> getAllPaymentMethods() throws DAOException;
}

