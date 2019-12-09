package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;

import java.util.List;

public interface AdditionalIngredientDAO {
    List<AdditionalIngredient> getAllAdditionalIngredients() throws DAOException;
   int decreasePortion(AdditionalIngredient additionalIngredient, int portion) throws DAOException;
   int addNewAdditionalIngredient(String nameAdditionalIngredient, int calories) throws  DAOException;
    boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws DAOException;
   AdditionalIngredient getAdditionalIngredient(int idAdditionalIngredient) throws DAOException;

}
