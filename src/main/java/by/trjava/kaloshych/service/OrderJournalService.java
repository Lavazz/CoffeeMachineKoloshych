package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.OrderJournal;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface OrderJournalService {
    double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException, SQLException;
    List<OrderJournal> getUserOrderHistory(int idUser) throws ServiceException;
}
