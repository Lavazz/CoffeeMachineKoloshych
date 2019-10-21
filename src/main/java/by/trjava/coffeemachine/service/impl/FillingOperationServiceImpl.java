package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.AdditionalIngredientDAO;
import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.FillingOperationDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Ingredient;
import by.trjava.coffeemachine.service.FillingOperationService;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.validation.FillingOperationValidator;

public class FillingOperationServiceImpl implements FillingOperationService {
    @Override
    public boolean fillingOperation(Ingredient ingredient, int amountPortion) throws ServiceException {
        boolean result = false;

//        if (!FillingOperationValidator.getInstance().validate(ingredient, amountPortion)) {
//            throw new ServiceException("Incorrect filling operation");
//        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        FillingOperationDAO fillingOperationDAO = daoFactory.getFillingOperationDAO();

        try {
            result = fillingOperationDAO.fillingOperation(ingredient, amountPortion);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
