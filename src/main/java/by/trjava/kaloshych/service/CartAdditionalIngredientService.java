package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface CartAdditionalIngredientService {

    void addAdditionalIngredientToCartAI(Cart cart, int idAdditionalIngredient) throws ServiceException;

    List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(int idUser) throws ServiceException;

}
