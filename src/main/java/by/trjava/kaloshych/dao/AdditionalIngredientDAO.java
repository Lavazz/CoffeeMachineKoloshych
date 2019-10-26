package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;

import java.util.List;

public interface AdditionalIngredientDAO {
    List<AdditionalIngredient> listAllAdditionalIngredient() throws DAOException, DAOException;
    boolean addAdditionalIngredient(String nameAdditionalIngredient, double price) throws DAOException, DAOException;
    boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException, DAOException;
}
