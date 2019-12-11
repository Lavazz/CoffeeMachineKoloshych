package by.trjava.kaloshych.service;

import by.trjava.kaloshych.service.exception.ServiceException;

public interface AccountService {

    void replenishBalance(int idAccountUser, String idPaymentMethod, String amountOfMoney) throws ServiceException;

    double getBalance(int idAccountUser) throws ServiceException;

}
