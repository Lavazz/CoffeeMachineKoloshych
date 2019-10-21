package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.OrderJournal;

import java.sql.SQLException;
import java.util.List;

public interface OrderJournalDAO {
    double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException, SQLException;
    List<OrderJournal> getUserOrderHistory(String userLogin);
}
