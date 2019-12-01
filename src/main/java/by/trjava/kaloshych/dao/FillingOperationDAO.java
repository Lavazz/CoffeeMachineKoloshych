package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;

import java.util.List;

public interface FillingOperationDAO {
    List<Component> getAllComponents() throws DAOException;
    boolean fillingOperation(int idComponent) throws DAOException;
    void addComponentToFillingOperation(Drink drink) throws DAOException;
    void addComponentToFillingOperation(AdditionalIngredient additionalIngredient) throws DAOException;
}
