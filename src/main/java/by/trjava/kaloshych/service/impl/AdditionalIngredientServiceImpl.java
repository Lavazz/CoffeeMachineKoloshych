package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.exception.DuplicateComponentException;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.AdditionalIngredientValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

/**
 * Represents methods for operation with AdditionalIngredient Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see AdditionalIngredient
 * @since JDK1.0
 */
public class AdditionalIngredientServiceImpl implements AdditionalIngredientService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final AdditionalIngredientDAO additionalIngredientDAO = daoFactory.getAdditionalIngredientDAO();
    private final FillingOperationDAO fillingOperationDAO = daoFactory.getFillingOperationDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

    @Override
    public void addNewAdditionalIngredient(String nameAdditionalIngredient, String calories) throws ServiceException {
        boolean duplicate;
        if (inputDataValidator.isEmpty(nameAdditionalIngredient)
                || inputDataValidator.isEmpty(calories)) {
            throw new EmptyDataException("Empty data");
        }
        if (!AdditionalIngredientValidator.getInstance().validate(nameAdditionalIngredient, Integer.parseInt(calories))) {
            throw new IncorrectComponentInformationException("incorrect information about component");
        }
        try {
            duplicate = additionalIngredientDAO.isExistsAdditionalIngredient(nameAdditionalIngredient);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in DrinkService can't check duplicate additional ingredient" + e);
        }

        if (duplicate) {
            throw new DuplicateComponentException("this additional ingredient  exists");
        }
        try {
            int idAdditionalIngredient = additionalIngredientDAO.addNewAdditionalIngredient(nameAdditionalIngredient, Integer.parseInt(calories));
            fillingOperationDAO.addComponentToFillingOperation(idAdditionalIngredient);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in additionalIngredientService can't add new ingredient" + e);
        }
    }


    @Override
    public List<AdditionalIngredient> getAllAdditionalIngredients() throws ServiceException {
        try {
            return additionalIngredientDAO.getAllAdditionalIngredients();
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in additionalIngredientService can't get all ingredients" + e);
        }
    }

}
