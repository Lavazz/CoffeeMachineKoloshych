package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.OrderJournalDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.service.OrderJournalService;
import by.trjava.coffeemachine.service.ServiceFactory;

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
    public List<OrderJournal> getUserOrderHistory(String userLogin) {
        return orderJournalDAO.getUserOrderHistory(userLogin);
    }
}
