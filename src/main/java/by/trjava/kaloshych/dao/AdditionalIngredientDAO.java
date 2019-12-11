package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;

import java.util.List;

public interface AdditionalIngredientDAO {

    List<AdditionalIngredient> getAllAdditionalIngredients() throws DAOException;

    int addNewAdditionalIngredient(String nameAdditionalIngredient, int calories) throws DAOException;

    boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException;

    AdditionalIngredient getAdditionalIngredient(int idAdditionalIngredient) throws DAOException;

    boolean isExistsAdditionalIngredient(String nameAdditionalIngredient) throws DAOException;

    int decreasePortion(int idAdditionalIngredient, int portion) throws DAOException;
}
