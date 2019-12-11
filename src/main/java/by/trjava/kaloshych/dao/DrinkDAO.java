package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Drink;

import java.util.List;

public interface DrinkDAO {

    List<Drink> getAllDrinks() throws DAOException;

    int decreasePortion(int idDrink, int portion) throws DAOException;

    double getDrinkPrice(int idDrink) throws DAOException;

    int getPortion(int idDrink) throws DAOException;

    int addNewDrink(String drink, double price, String description) throws DAOException;

    void deleteDrink(String drink) throws DAOException;

    Drink getDrink(int idDrink) throws DAOException;

    boolean checkDrinkById(int idComponent) throws DAOException;

    boolean isExistsDrink(String drinkName) throws DAOException;
}
