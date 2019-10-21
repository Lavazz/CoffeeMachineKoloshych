package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.AdditionalIngredientDAO;
import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;
import by.trjava.coffeemachine.service.AdditionalIngredientService;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.util.List;

public class AdditionalIngredientServiceImpl implements AdditionalIngredientService {
    @Override
    public List<AdditionalIngredient> listAllAdditionalIngredient() throws ServiceException, DAOException {

        DAOFactory daoFactory=DAOFactory.getInstance();
        AdditionalIngredientDAO additionalIngredientDAO=daoFactory.getAdditionalIngredientDAO();

       return   additionalIngredientDAO.listAllAdditionalIngredient();
    }
}
