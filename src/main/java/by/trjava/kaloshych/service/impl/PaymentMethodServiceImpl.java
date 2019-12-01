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
    public PaymentMethod createPaymentMethod(int idPaymentMethod) throws ServiceException {

        try {
            return paymentMethodDAO.createPaymentMethod(idPaymentMethod);
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() throws ServiceException {
        try {
            return paymentMethodDAO.getAllPaymentMethods();
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
    }


}
