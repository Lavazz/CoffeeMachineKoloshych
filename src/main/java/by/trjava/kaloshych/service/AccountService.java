package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.OrderJournal;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.sql.SQLException;

public interface AccountService {
    double increaseBalance(int idUser, String paymentMethod, double amountOfMoney) throws ServiceException, SQLException;

    double decreaseBalance(OrderJournal orderJournal, double totalCost) throws ServiceException, SQLException;
}
