package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.OrderDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.service.OrderService;

public class OrderServiceImpl implements OrderService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    OrderDAO orderDAO = daoFactory.getOrderDAO();



    @Override
    public int addOrder(int idUser) throws DAOException {
        int idOrder=0;
        //        if (!OrderValidator.getInstance().validate(user, drink, additionalIngredient, portion)) {
//            throw new ServiceException("Incorrect order");
//        }
        try {
            idOrder = orderDAO.addOrder(idUser);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return idOrder;
    }

}
