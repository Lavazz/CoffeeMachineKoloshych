package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.util.List;

public interface DrinkService {
    double getDrinkPrice (String drinkId) throws DAOException;
    List<Drink> listAllDrink() throws DAOException, ServiceException;
}
