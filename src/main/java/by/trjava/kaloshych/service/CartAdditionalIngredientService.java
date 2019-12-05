package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface CartAdditionalIngredientService {
    void addAdditionalIngredientToCartAI(Cart cart, int idAdditionalIngredient) throws ServiceException;
    void deleteAdditionalIngredientFromCartAI(int idCartAdditionalIngredient) throws ServiceException;
    List<CartAdditionalIngredient> getAllCartAdditionalIngredientByIdCart(Cart cart) throws ServiceException;
    List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws ServiceException;
    List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(int idUser) throws ServiceException;
}
