package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.PaymentMethodDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.PaymentMethod;
import by.trjava.kaloshych.service.PaymentMethodService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

/**
 * Represents methods for operation with PaymentMethod Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see PaymentMethod
 * @since JDK1.0
 */
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodDAO paymentMethodDAO = DAOFactory.getInstance().getPaymentMethodDAO();

    @Override
    public List<PaymentMethod> getAllPaymentMethods() throws ServiceException {
        try {
            return paymentMethodDAO.getAllPaymentMethods();
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in PaymentMethodService can't get all payment methods" + e);
        }
    }

}
