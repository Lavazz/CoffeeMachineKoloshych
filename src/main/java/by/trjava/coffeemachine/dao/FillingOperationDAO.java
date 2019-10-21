package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.Ingredient;

public interface FillingOperationDAO {
   // int checkСompleteness(Ingredient ingredient) throws DAOException;
    boolean fillingOperation(Ingredient ingredient, int amountPortion) throws DAOException;
}
