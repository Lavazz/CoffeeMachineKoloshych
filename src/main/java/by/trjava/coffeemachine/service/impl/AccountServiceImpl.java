package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.AccountDAO;
import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Account;
import by.trjava.coffeemachine.entity.Order;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.service.AccountService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.validation.AccountValidator;
import by.trjava.coffeemachine.service.validation.UserValidator;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
DAOFactory daoFactory = DAOFactory.getInstance();
AccountDAO accountDAO=daoFactory.getAccountDAO();




    @Override
    public double increaseBalance(String userLogin, String paymentMethod, double amountOfMoney) throws ServiceException {
//        if (!AccountValidator.getInstance().validate(userLogin, paymentMethod, amountOfMoney)) {
//            throw new ServiceException("Incorrect sum of money");
//        }
        try {
            return accountDAO.increaseBalance(userLogin, paymentMethod, amountOfMoney);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public double decreaseBalance(OrderJournal orderJournal, double totalCost) throws ServiceException, SQLException {
        //        if (!AccountValidator.getInstance().validate(orderJournal,totalCost)) {
//            throw new ServiceException("Incorrect sum of money");
//        }
        try {
            return accountDAO.decreaseBalance(orderJournal,totalCost);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    }

