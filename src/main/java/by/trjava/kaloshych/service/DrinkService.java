package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface DrinkService {
    double getDrinkPrice (String drinkId) throws DAOException;
    List<Drink> listAllDrink() throws DAOException, ServiceException;
}
