package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.entity.User;

import java.util.List;


public interface CartAdditionalIngredientDAO {
    boolean addAdditionalIngredientToCartAI(Cart cart, AdditionalIngredient additionalIngredient) throws DAOException;
    void deleteAdditionalIngredientFromCartAI(CartAdditionalIngredient CartAdditionalIngredient) throws DAOException;
    List<CartAdditionalIngredient> getAllCartAdditionalIngredientByCart(Cart cart) throws DAOException;
    List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws DAOException;
    List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(User user) throws DAOException;
    CartAdditionalIngredient getCartAdditionalIngredientsById(int idCartAdditionalIngredient) throws DAOException;
}
