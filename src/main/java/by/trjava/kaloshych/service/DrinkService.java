package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface DrinkService {
    List<Drink> getAllDrinks() throws ServiceException;
    int decreasePortion(Drink drink, int portion) throws ServiceException;
   double getDrinkPrice(int idDrink) throws ServiceException;
    void addNewDrink(String drink, String price, String description) throws ServiceException;
    void deleteDrink(String drink) throws ServiceException;
    boolean changePrice(String drink, int newPrice) throws ServiceException;
    Drink getDrink(int idDrink) throws ServiceException;
    boolean checkDrinkById(int idComponent) throws  ServiceException;
    Drink getDrink(Cart cart) throws ServiceException;
}
