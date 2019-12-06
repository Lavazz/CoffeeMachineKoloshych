package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface AdditionalIngredientService {
    List<AdditionalIngredient> getAllAdditionalIngredients() throws ServiceException;
   int decreasePortion(AdditionalIngredient additionalIngredient, int portion) throws ServiceException;
    void addNewAdditionalIngredient(String nameAdditionalIngredient, String calories) throws  ServiceException;
    boolean deleteAdditionalIngredient(int idAdditionalIngredient) throws ServiceException;
    AdditionalIngredient getAdditionalIngredient(int idAdditionalIngredient) throws ServiceException;
}
