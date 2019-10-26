package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Ingredient;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface FillingOperationService {
    int fillingOperation(int ingredient) throws ServiceException, DAOException;
    List<Ingredient> getIngredient() throws ServiceException, DAOException;
}
