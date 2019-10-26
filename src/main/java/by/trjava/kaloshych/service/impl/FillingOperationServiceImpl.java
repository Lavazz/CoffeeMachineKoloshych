package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.FillingOperationDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Ingredient;
import by.trjava.kaloshych.service.FillingOperationService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public class FillingOperationServiceImpl implements FillingOperationService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    FillingOperationDAO fillingOperationDAO = daoFactory.getFillingOperationDAO();

    @Override
    public int fillingOperation(int ingredient) throws ServiceException, DAOException {

//        if (!FillingOperationValidator.getInstance().validate(ingredient, amountPortion)) {
//            throw new ServiceException("Incorrect filling operation");
//        }

            return    fillingOperationDAO.fillingOperation(ingredient);


    }

    @Override
    public List<Ingredient> getIngredient() throws ServiceException, DAOException {
       return fillingOperationDAO.getIngredient();
    }
}
