package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.DrinkService;
import by.trjava.kaloshych.service.exception.DuplicateComponentException;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.DrinkValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

/**
 * Represents methods for operation with Drink Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Drink
 * @since JDK1.0
 */
public class DrinkServiceImpl implements DrinkService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final DrinkDAO drinkDAO = daoFactory.getDrinkDAO();
    private final FillingOperationDAO fillingOperationDAO = daoFactory.getFillingOperationDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

    @Override
    public List<Drink> getAllDrinks() throws ServiceException {
        try {
            return drinkDAO.getAllDrinks();
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in DrinkService can't get all drinks" + e);
        }
    }


    @Override
    public void addNewDrink(String drinkName, String price, String description) throws ServiceException {
        boolean duplicate;
        if (inputDataValidator.isEmpty(drinkName)
                || inputDataValidator.isEmpty(price)
                || inputDataValidator.isEmpty(description)) {
            throw new EmptyDataException("Empty data");
        }
        if (!DrinkValidator.getInstance().validate(drinkName, Double.parseDouble(price), description)) {
            throw new IncorrectComponentInformationException("incorrect information about component");
        }

        try {
            duplicate = drinkDAO.isExistsDrink(drinkName);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in DrinkService can't check duplicate drink" + e);
        }

        if (duplicate) {
            throw new DuplicateComponentException("incorrect information about component");
        }
        try {
            int idDrink = drinkDAO.addNewDrink(drinkName, Double.parseDouble(price), description);
            fillingOperationDAO.addComponentToFillingOperation(idDrink);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in DrinkService can't add new drink" + e);
        }
    }

}
