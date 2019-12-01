package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.Drink;

public interface ComponentDAO {
    void deleteComponent(Component component, String type) throws DAOException;

    void deleteComponentFromFillingOperation(Component component, String type) throws DAOException;

}
