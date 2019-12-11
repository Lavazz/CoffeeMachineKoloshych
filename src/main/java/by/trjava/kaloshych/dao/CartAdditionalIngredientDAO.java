package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;

import java.util.List;


public interface CartAdditionalIngredientDAO {
    void addAdditionalIngredientToCartAI(Cart cart, int AdditionalIngredient) throws DAOException;

    void deleteAdditionalIngredientFromCartAI(int idCartAdditionalIngredient) throws DAOException;

    List<CartAdditionalIngredient> getAllCartAdditionalIngredientByCart(Cart cart) throws DAOException;

    List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws DAOException;

    List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(int idUser) throws DAOException;

    CartAdditionalIngredient getCartAdditionalIngredientsById(int idCartAdditionalIngredient) throws DAOException;
}
