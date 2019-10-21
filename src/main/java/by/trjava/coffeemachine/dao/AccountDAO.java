package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Account;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.entity.Order;

import java.sql.Date;
import java.sql.SQLException;

public interface AccountDAO {
    double increaseBalance(String userLogin, String paymentMethod, double amountOfMoney) throws DAOException, SQLException;

    double decreaseBalance(OrderJournal orderJournal, double totalCost) throws DAOException, SQLException;
}
