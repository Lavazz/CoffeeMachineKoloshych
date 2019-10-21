package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.util.List;

public interface AdditionalIngredientService {
    public List<AdditionalIngredient> listAllAdditionalIngredient() throws ServiceException, DAOException;
}
