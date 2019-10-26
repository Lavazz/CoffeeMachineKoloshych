package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.OrderJournal;

public interface AccountDAO {
    double increaseBalance(int idUser, String paymentMethod, double amountOfMoney) throws DAOException;

    double decreaseBalance(OrderJournal orderJournal, double totalCost) throws DAOException;
}
