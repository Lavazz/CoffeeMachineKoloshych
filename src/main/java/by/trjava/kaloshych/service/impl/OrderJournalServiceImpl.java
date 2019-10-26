package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.OrderJournalDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.OrderJournal;
import by.trjava.kaloshych.service.OrderJournalService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class OrderJournalServiceImpl implements OrderJournalService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    OrderJournalDAO orderJournalDAO = daoFactory.getOrderJournalDAO();

    @Override
    public double addOrderInJournal(List<OrderJournal> listOrderJournal) throws DAOException, SQLException {
//        if (!OrderJournalValidator.getInstance().validate(listOrderJournal)) {
//            throw new ServiceException("Incorrect name of drink");
//        }
        return   orderJournalDAO.addOrderInJournal(listOrderJournal);
    }


    @Override
    public List<OrderJournal> getUserOrderHistory(int idUser) throws ServiceException {
        try {
            return orderJournalDAO.getUserOrderHistory(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
