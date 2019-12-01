package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AdditionalIngredientDAO;
import by.trjava.kaloshych.dao.CartAdditionalIngredientDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.SQLAdditionalIngredientDAO;
import by.trjava.kaloshych.dao.impl.SQLCartAdditionalIngredientDAO;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.CartAdditionalIngredientService;
import by.trjava.kaloshych.service.exception.InsufficientPortionException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.CartAdditionalIngredientValidator;

import java.util.List;

public class CartAdditionalIngredientServiceImpl implements CartAdditionalIngredientService {

    private static final CartAdditionalIngredientDAO cartAdditionalIngredientDAO = DAOFactory.getInstance().getCartAdditionalIngredientDAO();

    @Override
    public void addAdditionalIngredientToCartAI(Cart cart, int idAdditionalIngredient) throws ServiceException {
        AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
        int newPortion;

        try {
            AdditionalIngredient additionalIngredient = additionalIngredientDAO.createAdditionalIngredient(idAdditionalIngredient);
            cartAdditionalIngredientDAO.addAdditionalIngredientToCartAI(cart, additionalIngredient);
            newPortion = additionalIngredientDAO.decreasePortion(additionalIngredient, cart.getPortion());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (!CartAdditionalIngredientValidator.getInstance().isSufficientPortion(newPortion)) {
            throw new InsufficientPortionException("In coffee machine insufficient portion of this additional ingredient");
        }
    }

    @Override
    public void deleteAdditionalIngredientFromCartAI(int idCartAdditionalIngredient)  {
//cartAdditionalIngredientDAO.deleteAdditionalIngredientFromCartAI(idCartAdditionalIngredient);
    }

    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredientByIdCart(Cart cart) throws ServiceException {
        try {
            return cartAdditionalIngredientDAO.getAllCartAdditionalIngredientByCart(cart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getAllCartAdditionalIngredients() throws ServiceException {
        try {
            return cartAdditionalIngredientDAO.getAllCartAdditionalIngredients();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CartAdditionalIngredient> getCartAdditionalIngredientsByUser(User user) throws ServiceException {
        try {
            return cartAdditionalIngredientDAO.getCartAdditionalIngredientsByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
