package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.DrinkService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public class DrinkServiceImpl implements DrinkService {
    private static final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();

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
