package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface AdditionalIngredientService {

    List<AdditionalIngredient> getAllAdditionalIngredients() throws ServiceException;

    void addNewAdditionalIngredient(String nameAdditionalIngredient, String calories) throws ServiceException;

}
