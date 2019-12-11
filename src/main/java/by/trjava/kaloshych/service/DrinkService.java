package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface DrinkService {

    List<Drink> getAllDrinks() throws ServiceException;

    void addNewDrink(String drink, String price, String description) throws ServiceException;

}
