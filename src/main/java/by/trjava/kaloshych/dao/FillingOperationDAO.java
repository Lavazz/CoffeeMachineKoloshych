package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Ingredient;

import java.util.List;

public interface FillingOperationDAO {
    int fillingOperation(int ingredient) throws DAOException;
    List<Ingredient> getIngredient() throws DAOException;
}
