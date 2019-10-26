package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.OrderJournal;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    private static final AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();

    @Override
    public double increaseBalance(int idUser, String paymentMethod, double amountOfMoney) throws ServiceException {
//        if (!AccountValidator.getInstance().validate(userLogin, paymentMethod, amountOfMoney)) {
//            throw new ServiceException("Incorrect sum of money");
//        }
        try {
            return accountDAO.increaseBalance(idUser, paymentMethod, amountOfMoney);
        } catch (DAOException e) {
            throw new ServiceException( e);
        }
    }

    @Override
    public double decreaseBalance(OrderJournal orderJournal, double totalCost) throws ServiceException, SQLException {
        //        if (!AccountValidator.getInstance().validate(orderJournal,totalCost)) {
//            throw new ServiceException("Incorrect sum of money");
//        }
        try {
            return accountDAO.decreaseBalance(orderJournal,totalCost);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    }

