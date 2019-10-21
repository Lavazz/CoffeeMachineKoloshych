package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.OrderJournal;

import java.sql.SQLException;
import java.util.List;

public interface OrderJournalService {
    double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException, SQLException;
    List<OrderJournal> getUserOrderHistory(String userLogin);
}
