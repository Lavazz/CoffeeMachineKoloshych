package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.sql.SQLException;

public interface AccountService {
    void addNewAccount(AccountUser accountUser) throws ServiceException;
    void replenishBalance(int idAccountUser, String idPaymentMethod, String amountOfMoney) throws ServiceException;
    boolean decreaseBalance(Order order) throws  ServiceException;
    double getBalance (int idAccountUser) throws ServiceException;
}
