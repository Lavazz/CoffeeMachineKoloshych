package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.AdditionalIngredientValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

public class AdditionalIngredientServiceImpl implements AdditionalIngredientService {

   private final AdditionalIngredientDAO additionalIngredientDAO=DAOFactory.getInstance().getAdditionalIngredientDAO();
    private  final FillingOperationDAO fillingOperationDAO=DAOFactory.getInstance().getFillingOperationDAO();
    private  final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

    @Override
    public List<AdditionalIngredient> getAllAdditionalIngredients() throws ServiceException {
        try {
            return additionalIngredientDAO.getAllAdditionalIngredients();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int decreasePortion(AdditionalIngredient additionalIngredient, int portion) throws ServiceException {
        try {
         return  additionalIngredientDAO.decreasePortion(additionalIngredient, portion);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void addNewAdditionalIngredient(String nameAdditionalIngredient, String calories) throws ServiceException {
        if (inputDataValidator.isEmpty(nameAdditionalIngredient)
                || inputDataValidator.isEmpty(calories)) {
            throw new EmptyDataException("Empty data");
        }
        if(!AdditionalIngredientValidator.getInstance().validate(nameAdditionalIngredient, Integer.parseInt(calories))){
            throw new IncorrectComponentInformationException("incorrect information about component");
        }
        try {
          AdditionalIngredient additionalIngredient=  additionalIngredientDAO.addNewAdditionalIngredient(nameAdditionalIngredient, Integer.parseInt(calories));
            fillingOperationDAO.addComponentToFillingOperation(additionalIngredient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws ServiceException {
        try {
            return additionalIngredientDAO.deleteAdditionalIngredient(idAdditionalIngredient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AdditionalIngredient createAdditionalIngredient(int idAdditionalIngredient) throws ServiceException {
        try {
            return additionalIngredientDAO.createAdditionalIngredient(idAdditionalIngredient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
