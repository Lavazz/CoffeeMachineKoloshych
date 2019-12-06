package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.PaymentMethod;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod getPaymentMethod(int idPaymentMethod) throws ServiceException;
    List<PaymentMethod> getAllPaymentMethods() throws ServiceException;
}
