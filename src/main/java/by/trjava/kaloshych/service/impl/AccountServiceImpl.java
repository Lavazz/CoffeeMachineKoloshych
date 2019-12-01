package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.SmallAmountException;
import by.trjava.kaloshych.service.validation.AccountValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    private static final AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
private static final InputDataValidator dataValidator=InputDataValidator.getInstance();

    @Override
    public void addNewAccount(AccountUser accountUser) throws ServiceException {
        try {
            accountDAO.addNewAccount(accountUser);
        } catch (DAOException e) {
            throw new ServiceException( e);
        }
    }

    @Override
    public void replenishBalance(AccountUser accountUser, String idPaymentMethod, String amountOfMoney) throws ServiceException {
        if (dataValidator.isEmpty(accountUser)||dataValidator.isEmpty(idPaymentMethod)||dataValidator.isEmpty(amountOfMoney)) {
            throw new EmptyDataException("Empty data");
        }
        if (!AccountValidator.getInstance().validate(Double.parseDouble(amountOfMoney))) {
            throw new SmallAmountException("Incorrect sum of money");
        }
        try {
            accountDAO.replenishBalance(accountUser, Integer.parseInt(idPaymentMethod), Double.parseDouble(amountOfMoney));
        } catch (DAOException e) {
            throw new ServiceException( e);
        }
    }

    @Override
    public boolean decreaseBalance(Order order) throws ServiceException {
        try {
            return  accountDAO.decreaseBalance(order)>0;
        } catch (DAOException e) {
            throw new ServiceException( e);
        }
    }

    @Override
    public double getBalance(AccountUser accountUser) throws ServiceException {
        try {
            return accountDAO.getBalance(accountUser.getUser());
        } catch (DAOException e) {
            throw new ServiceException( e);
        }
    }

}

