package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Drink;

import java.util.List;

public interface DrinkDAO {
    double getDrinkPrice (String drinkId) throws DAOException;
    List<Drink> listAllDrink() throws DAOException;
}
