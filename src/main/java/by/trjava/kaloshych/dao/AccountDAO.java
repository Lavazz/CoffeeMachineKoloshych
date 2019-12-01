package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;

public interface AccountDAO {
    void addNewAccount(AccountUser accountUser) throws DAOException;
    void replenishBalance(AccountUser accountUser, int paymentMethod, double amountOfMoney) throws DAOException;
     double decreaseBalance(Order order)throws DAOException;
    double getBalance (User user) throws DAOException;
}
