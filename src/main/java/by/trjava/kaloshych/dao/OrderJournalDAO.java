package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.OrderJournal;

import java.sql.SQLException;
import java.util.List;

public interface OrderJournalDAO {
    double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException, SQLException;
    List<OrderJournal> getUserOrderHistory(int idUser) throws DAOException;
}
