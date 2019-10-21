package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.entity.Order;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    int addOrder(int idUser) throws DAOException;
}
