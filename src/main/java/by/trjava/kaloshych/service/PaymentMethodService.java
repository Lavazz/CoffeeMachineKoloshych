package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.PaymentMethod;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethod> getAllPaymentMethods() throws ServiceException;
}
