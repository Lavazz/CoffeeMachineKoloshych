package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.DrinkService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.DrinkValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

public class DrinkServiceImpl implements DrinkService {

    private final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
   private final FillingOperationDAO fillingOperationDAO=DAOFactory.getInstance().getFillingOperationDAO();
    private  final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

    @Override
    public List<Drink> getAllDrinks() throws ServiceException {
        try {
            return drinkDAO.getAllDrinks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int decreasePortion(Drink drink, int portion) throws ServiceException {
        try {
            return drinkDAO.decreasePortion(drink, portion);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double getDrinkPrice(int idDrink) throws ServiceException {
        try {
            return drinkDAO.getDrinkPrice(idDrink);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addNewDrink(String drinkName, String price, String description) throws ServiceException {
        if (inputDataValidator.isEmpty(drinkName)
                || inputDataValidator.isEmpty(price)
                || inputDataValidator.isEmpty(description)) {
            throw new EmptyDataException("Empty data");
        }
        if(!DrinkValidator.getInstance().validate(drinkName, Double.parseDouble(price), description)){
            throw new IncorrectComponentInformationException("incorrect information about component");
        }

        if(!DrinkValidator.getInstance().validate(drinkName, Double.parseDouble(price), description)){
            throw new IncorrectComponentInformationException("incorrect information about component");
        }

        try {
         Drink drink=drinkDAO.addNewDrink(drinkName, Double.parseDouble(price), description);
            fillingOperationDAO.addComponentToFillingOperation(drink);
        } catch (DAOException e) {
           throw  new ServiceException(e);
        }
    }

    @Override
    public void deleteDrink(String drink) throws ServiceException {
        try {
            drinkDAO.deleteDrink(drink);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changePrice(String drink, int newPrice) throws ServiceException {
        try {
          return   drinkDAO.changePrice(drink, newPrice);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Drink getDrink(int idDrink) throws ServiceException {
        try {
          return   drinkDAO.getDrink(idDrink);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkDrinkById(int idComponent) throws ServiceException {
        try {
         return  drinkDAO.checkDrinkById(idComponent);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Drink getDrink(Cart cart) throws ServiceException {
        try {
          return   drinkDAO.getDrink(cart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
