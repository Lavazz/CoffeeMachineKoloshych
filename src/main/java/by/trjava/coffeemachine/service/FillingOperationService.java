package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.entity.Ingredient;
import by.trjava.coffeemachine.service.exception.ServiceException;

public interface FillingOperationService {
    boolean fillingOperation(Ingredient ingredient, int amountPortion) throws ServiceException;
}
