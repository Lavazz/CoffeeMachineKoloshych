package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;

import java.util.List;

public interface DrinkDAO {

    List<Drink> getAllDrinks() throws DAOException;
    int decreasePortion(Drink drink, int portion) throws DAOException;
    double getDrinkPrice(int idDrink) throws DAOException;
    int getPortion(Drink drink) throws DAOException;
   Drink addNewDrink(String drink, double price, String description) throws DAOException;
    void deleteDrink(String drink) throws DAOException;
    boolean changePrice(String drink, int newPrice) throws DAOException;
    Drink getDrink(int idDrink) throws DAOException;
    boolean checkDrinkById(int idComponent) throws DAOException;
    Drink getDrink(Cart cart) throws DAOException;

}
