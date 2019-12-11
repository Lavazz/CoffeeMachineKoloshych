package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Order;

public interface AccountDAO {

    void addNewAccount(int idAccountUser) throws DAOException;

    void replenishBalance(int idAccountUser, int idPaymentMethod, double amountOfMoney) throws DAOException;

    double decreaseBalance(Order order, int idAccountUser) throws DAOException;

    double getBalance(int idAccountUser) throws DAOException;
}
