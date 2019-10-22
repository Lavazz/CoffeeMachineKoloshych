package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Account;
import by.trjava.coffeemachine.entity.Order;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.sql.SQLException;

public interface AccountService {
    double increaseBalance(int idUser, String paymentMethod, double amountOfMoney) throws ServiceException, SQLException;

    double decreaseBalance(OrderJournal orderJournal, double totalCost) throws ServiceException, SQLException;
}
