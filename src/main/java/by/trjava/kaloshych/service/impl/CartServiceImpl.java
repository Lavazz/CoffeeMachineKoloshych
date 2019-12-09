package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.exception.*;
import by.trjava.kaloshych.service.validation.CartValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

public class CartServiceImpl implements CartService {

    private static final int PORTION_MODIFICATION = 1;
    private static final String SIGN_PLUS="plus";
    private static final String SIGN_MINUS="minus";
    private final CartDAO cartDAO = DAOFactory.getInstance().getCartDAO();
    private final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();
    private final CartUserDAO cartUserDAO = DAOFactory.getInstance().getCartUserDAO();
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public Cart addDrinkToCart(int idCartUser, String idDrink, String portion) throws ServiceException {
        int currentPortion;
        Drink drink;
        CartUser cartUser;

        if (inputDataValidator.isEmpty(idDrink) || inputDataValidator.isEmpty(portion)
                || inputDataValidator.isEmpty(idCartUser)) {
            throw new EmptyDataException("Empty data");
        }
        if (!CartValidator.getInstance().validate(portion)) {
            throw new WrongPortionException("Wrong number of portions");
        }

        try {
            cartUser = cartUserDAO.getCartUserById(idCartUser);
            drink = drinkDAO.getDrink(Integer.parseInt(idDrink));
            currentPortion = drinkDAO.getPortion(drink);
            System.out.println("current portion=" + currentPortion);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (!CartValidator.getInstance().isSufficientPortion(currentPortion, Integer.parseInt(portion))) {
            throw new InsufficientPortionException("In coffee machine insufficient portion of this drink");
        }

        try {
            drinkDAO.decreasePortion(drink, Integer.parseInt(portion));

            int idCart = cartDAO.addDrinkToCart(cartUser, drink, Integer.parseInt(portion));
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
            Cart cart = cartDAO.getCartById(Integer.parseInt(idCart));
            cartDAO.deleteDrinkFromCart(cart);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't delete drink from cart" + e);
        }
    }

    @Override
    public boolean changePortion(String idCart, String sign) throws ServiceException {
        try {
            Cart cart = cartDAO.getCartById(Integer.parseInt(idCart));
            int portion = cart.getPortion();
            if (sign.equals(SIGN_PLUS)) {
                portion += PORTION_MODIFICATION;
            } else if (sign.equals(SIGN_MINUS)) {
                portion -= PORTION_MODIFICATION;
            }
            if (!CartValidator.getInstance().validate(portion)) {
                throw new WrongPortionException("Wrong number of portions");
            }
            int currentPortion = drinkDAO.getPortion(cart.getDrink());
            if (!CartValidator.getInstance().isSufficientPortion(currentPortion, portion)) {
                throw new InsufficientPortionException("In coffee machine insufficient portion of this drink");
            }
            return cartDAO.changePortion(cart, portion);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't change portion" + e);
        }
    }


    @Override
    public List<Cart> getAllCarts(int idCartUser) throws ServiceException {
        try {
            CartUser cartUser = cartUserDAO.getCartUserById(idCartUser);
            return cartDAO.getAllCarts(cartUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't get all carts" + e);
        }
    }

    @Override
    public Cart getCartById(int idCart) throws ServiceException {
        try {
            return cartDAO.getCartById(idCart);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't get cart by id" + e);
        }
    }

    @Override
    public int getPortionByCart(Cart cart) throws ServiceException {
        try {
            return cartDAO.getPortionByCart(cart);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't get portion" + e);
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
            User user = userDAO.getUserById(idUser);
            return cartDAO.getAllCartsByUser(user);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartService can't get all carts by user" + e);
        }
    }
}
