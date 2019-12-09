package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.PaymentMethodDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.PaymentMethod;
import by.trjava.kaloshych.service.PaymentMethodService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public class PaymentMethodServiceImpl implements PaymentMethodService {
    private static final  PaymentMethodDAO paymentMethodDAO= DAOFactory.getInstance().getPaymentMethodDAO();

    @Override
    public PaymentMethod getPaymentMethod(int idPaymentMethod) throws ServiceException {

        try {
            return paymentMethodDAO.getPaymentMethod(idPaymentMethod);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in PaymentMethodService can't get payment method" + e);
        }
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() throws ServiceException {
        try {
            return paymentMethodDAO.getAllPaymentMethods();
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in PaymentMethodService can't get all payment methods" + e);
        }
    }


}
