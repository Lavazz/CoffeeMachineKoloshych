package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;

import java.util.List;

public interface AdditionalIngredientDAO {
    public List<AdditionalIngredient> listAllAdditionalIngredient() throws DAOException;
}
