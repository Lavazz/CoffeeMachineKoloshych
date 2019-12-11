package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Account;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.SmallAmountException;
import by.trjava.kaloshych.service.validation.AccountValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

/**
 * Represents methods for operation with Account Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Account
 * @since JDK1.0
 */
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
    private final InputDataValidator dataValidator = InputDataValidator.getInstance();

    @Override
    public void replenishBalance(int idAccountUser, String idPaymentMethod, String amountOfMoney) throws ServiceException {
        if (dataValidator.isEmpty(idPaymentMethod) || dataValidator.isEmpty(amountOfMoney)) {
            throw new EmptyDataException("Empty data");
        }
        if (!AccountValidator.getInstance().validate(Double.parseDouble(amountOfMoney))) {
            throw new SmallAmountException("Incorrect sum of money");
        }
        try {
            accountDAO.replenishBalance(idAccountUser, Integer.parseInt(idPaymentMethod), Double.parseDouble(amountOfMoney));
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in accountService, can't replenish balance" + e);
        }
    }


    @Override
    public double getBalance(int idAccountUser) throws ServiceException {
        try {
            return accountDAO.getBalance(idAccountUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in accountService can't get balance" + e);
        }
    }

}