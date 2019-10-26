package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.service.exception.AdditionalIngredientServiceException;

import java.util.List;

public interface AdditionalIngredientService {
   List<AdditionalIngredient> listAllAdditionalIngredient() throws AdditionalIngredientServiceException;
    void addAdditionalIngredient(String nameAdditionalIngredient, double price) throws AdditionalIngredientServiceException;
    void deleteAdditionalIngredient(int idAdditionalIngredient) throws AdditionalIngredientServiceException;
}
