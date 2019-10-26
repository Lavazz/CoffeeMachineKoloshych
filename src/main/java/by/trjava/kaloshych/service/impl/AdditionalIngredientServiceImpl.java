package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.exception.AdditionalIngredientServiceException;

import java.util.List;

public class AdditionalIngredientServiceImpl implements AdditionalIngredientService {

   private static final AdditionalIngredientDAO additionalIngredientDAO=DAOFactory.getInstance().getAdditionalIngredientDAO();
    @Override
    public List<AdditionalIngredient> listAllAdditionalIngredient() throws AdditionalIngredientServiceException {

        //validation



        try {
            return   additionalIngredientDAO.listAllAdditionalIngredient();
        } catch (DAOException e) {
           throw new AdditionalIngredientServiceException (e);
        }
    }

    @Override
    public void addAdditionalIngredient(String nameAdditionalIngredient, double price) throws AdditionalIngredientServiceException {

        //validation
        try {
            additionalIngredientDAO.addAdditionalIngredient(nameAdditionalIngredient, price);
        } catch (DAOException e) {
            throw new AdditionalIngredientServiceException(e);
        }
    }

    @Override
    public void deleteAdditionalIngredient(int idAdditionalIngredient) throws AdditionalIngredientServiceException{
        //validation

        try {
            additionalIngredientDAO.deleteAdditionalIngredient(idAdditionalIngredient);
        } catch ( DAOException e) {
            throw new AdditionalIngredientServiceException(e);
        }
    }
}
