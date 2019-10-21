package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.entity.Order;
import by.trjava.coffeemachine.entity.User;

import java.util.List;

public interface OrderDAO {
    int addOrder(int idUser) throws DAOException;
}
