package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.OrderDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.entity.Order;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.OrderService;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.validation.OrderValidator;

import java.util.List;

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
