package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.InsufficientPortionException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.WrongPortionException;
import by.trjava.kaloshych.service.validation.CartValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

/**
 * Represents methods for operation with Cart Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Cart
 * @since JDK1.0
 */
public class CartServiceImpl implements CartService {

    private static final int PORTION_MODIFICATION = 1;
    private static final String SIGN_PLUS = "plus";
    private static final String SIGN_MINUS = "minus";
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final CartDAO cartDAO = daoFactory.getCartDAO();
    private final DrinkDAO drinkDAO = daoFactory.getDrinkDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();

    @Override
    public Cart addDrinkToCart(int idCartUser, String idDrinkString, String portion) throws ServiceException {
        int currentPortion;

        if (inputDataValidator.isEmpty(idDrinkString) || inputDataValidator.isEmpty(portion)
                || inputDataValidator.isEmpty(idCartUser)) {
            throw new EmptyDataException("Empty data");
        }
        if (!CartValidator.getInstance().validate(portion)) {
            throw new WrongPortionException("Wrong number of portions");
        }

        int idDrink = Integer.parseInt(idDrinkString);
        try {
            currentPortion = drinkDAO.getPortion(idDrink);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (!CartValidator.getInstance().isSufficientPortion(currentPortion, Integer.parseInt(portion))) {
            throw new InsufficientPortionException("In coffee machine insufficient portion of this drink");
        }

        try {
            drinkDAO.decreasePortion(idDrink, Integer.parseInt(portion));

            int idCart = cartDAO.addDrinkToCart(idCartUser, idDrink, Integer.parseInt(portion));
            return cartDAO.getCartById(idCart);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't add drink to cart" + e);
        }
    }

    @Override
    public void deleteDrinkFromCart(String idCart) throws ServiceException {
        if (inputDataValidator.isEmpty(idCart)) {
            throw new EmptyDataException("Empty data");
        }
        try {
            cartDAO.deleteDrinkFromCart(Integer.parseInt(idCart));
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't delete drink from cart" + e);
        }
    }

    @Override
    public boolean changePortion(int idCart, String sign) throws ServiceException {
        try {
            Cart cart = cartDAO.getCartById(idCart);
            int portion = cart.getPortion();
            if (sign.equals(SIGN_PLUS)) {
                portion += PORTION_MODIFICATION;
            } else if (sign.equals(SIGN_MINUS)) {
                portion -= PORTION_MODIFICATION;
            }
            if (!CartValidator.getInstance().validate(portion)) {
                throw new WrongPortionException("Wrong number of portions");
            }
            int currentPortion = drinkDAO.getPortion(cart.getDrink().getIdComponent());
            if (!CartValidator.getInstance().isSufficientPortion(currentPortion, portion)) {
                throw new InsufficientPortionException("In coffee machine insufficient portion of this drink");
            }
            return cartDAO.changePortion(idCart, portion);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't change portion" + e);
        }
    }


    @Override
    public List<Cart> getAllCarts(int idCartUser) throws ServiceException {
        try {
            return cartDAO.getAllCarts(idCartUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't get all carts" + e);
        }
    }

    @Override
    public double getTotalCost(int idCartUser) throws ServiceException {
        double totalCost = 0;
        List<Cart> carts = getAllCarts(idCartUser);
        for (Cart cart : carts) {
            totalCost += cart.getDrink().getPrice() * cart.getPortion();
        }
        return totalCost;
    }

    @Override
    public List<Cart> getAllCartsByUser(int idUser) throws ServiceException {
        try {
            return cartDAO.getAllCartsByUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't get all carts by user" + e);
        }
    }
}
