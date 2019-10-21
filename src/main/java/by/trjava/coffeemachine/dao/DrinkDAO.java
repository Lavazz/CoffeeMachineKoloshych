package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Drink;

import java.util.List;

public interface DrinkDAO {
    double getDrinkPrice (String drinkId) throws DAOException;
    List<Drink> listAllDrink() throws DAOException;
}
