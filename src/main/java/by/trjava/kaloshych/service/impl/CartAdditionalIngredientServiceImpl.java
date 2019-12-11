package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartAdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.service.CartAdditionalIngredientService;
import by.trjava.kaloshych.service.exception.InsufficientPortionException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.CartAdditionalIngredientValidator;

import java.util.List;

/**
 * Represents methods for operation with CartAdditionalIngredient Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see CartAdditionalIngredient
 * @since JDK1.0
 */
public class CartAdditionalIngredientServiceImpl implements CartAdditionalIngredientService {

    private final CartAdditionalIngredientDAO cartAdditionalIngredientDAO = DAOFactory.getInstance().getCartAdditionalIngredientDAO();

    @Override
    public void addAdditionalIngredientToCartAI(Cart cart, int idAdditionalIngredient) throws ServiceException {
        AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
        int newPortion;

        try {
            cartAdditionalIngredientDAO.addAdditionalIngredientToCartAI(cart, idAdditionalIngredient);
            newPortion = additionalIngredientDAO.decreasePortion(idAdditionalIngredient, cart.getPortion());
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in cartAdditionalIngredientService can't add to cart" + e);
        }
        if (!CartAdditionalIngredientValidator.getInstance().isSufficientPortion(newPortion)) {
            throw new InsufficientPortionException("In coffee machine insufficient portion of this additional ingredient");
        }
    }


    @Override
    public List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(int idUser) throws ServiceException {
        try {
            return cartAdditionalIngredientDAO.getCartAdditionalIngredientsByUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in cartAdditionalIngredientService can't get  CartAI" + e);
        }
    }

}
