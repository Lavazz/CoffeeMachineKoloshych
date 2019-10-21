package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.DrinkDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.service.DrinkService;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.validation.DrinkValidator;

import java.util.ArrayList;
import java.util.List;

public class DrinkServiceImpl implements DrinkService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    DrinkDAO drinkDAO = daoFactory.getDrinkDAO();

    @Override
    public double getDrinkPrice(String drinkId) throws DAOException {
//        if (!DrinkValidator.getInstance().validate(drinkName)) {
//            throw new ServiceException("Incorrect name of drink");
//        }
        return drinkDAO.getDrinkPrice(drinkId);
    }

    @Override
    public List<Drink> listAllDrink() throws ServiceException {

        try {
            return drinkDAO.listAllDrink();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
