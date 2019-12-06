package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.*;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.InsufficientMoneyException;
import by.trjava.kaloshych.service.exception.InsufficientPortionException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.InputDataValidator;
import by.trjava.kaloshych.service.validation.OrderValidator;

import java.sql.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
    private final CartDAO cartDAO = DAOFactory.getInstance().getCartDAO();
    private final CartAdditionalIngredientDAO cartAdditionalIngredientDAO = DAOFactory.getInstance().getCartAdditionalIngredientDAO();
    private final AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();
    private final CartUserDAO cartUserDAO = DAOFactory.getInstance().getCartUserDAO();
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public void addOrder(int idCartUser) throws ServiceException {
        final CartService cartService = ServiceFactory.getInstance().getCartService();
        double totalCost;
        try {
            CartUser cartUser = cartUserDAO.getCartUserById(idCartUser);
            double currentBalance = accountDAO.getBalance(cartUser.getUser());
            totalCost = cartService.getTotalCost(cartUser.getIdCartUser());
            if (currentBalance < totalCost) {
                throw new InsufficientMoneyException("In account insufficient money");
            }
            Order order = orderDAO.addOrder(cartUser, totalCost);
            accountDAO.decreaseBalance(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addOrderWithAdditionalIngredient(CartUser cartUser) throws ServiceException {

        AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
        int newPortion;

        try {

            List<Cart> carts = cartDAO.getAllCarts(cartUser);
            for (Cart cart : carts) {

                List<CartAdditionalIngredient> cartAdditionalIngredients = cartAdditionalIngredientDAO.getAllCartAdditionalIngredientByCart(cart);
                for (CartAdditionalIngredient cartAdditionalIngredient : cartAdditionalIngredients) {
                    newPortion = additionalIngredientDAO.decreasePortion(cartAdditionalIngredient.getAdditionalIngredient(), cart.getPortion());
                    if (newPortion < 1) {
                        throw new InsufficientPortionException("In coffee machine insufficient portion of this additional ingredient");
                    }
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllOrdersByUser(int idUser) throws ServiceException {
        try {
            User user = userDAO.getUserById(idUser);
            return orderDAO.getAllOrdersByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkIdOrder(int idOrder) throws ServiceException {
        try {
            return orderDAO.checkIdOrder(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void cancelOrder(int idCartUser) throws ServiceException {
        try {
            CartUser cartUser = cartUserDAO.getCartUserById(idCartUser);
            List<Cart> carts = cartDAO.getAllCarts(cartUser);
            for (Cart cart : carts) {
                List<CartAdditionalIngredient> cartAdditionalIngredients = cartAdditionalIngredientDAO.getAllCartAdditionalIngredientByCart(cart);
                cartDAO.deleteDrinkFromCart(cart);
                for (CartAdditionalIngredient cartAdditionalIngredient : cartAdditionalIngredients) {
                    cartAdditionalIngredientDAO.deleteAdditionalIngredientFromCartAI(cartAdditionalIngredient);
                }
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Date getDateOrderByIdCartUser(CartUser cartUser) throws ServiceException {
        try {
            return orderDAO.getDateOrderByIdCartUser(cartUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double getTotalCostByIdCartUser(CartUser cartUser) throws ServiceException {
        try {
            return orderDAO.getTotalCostByIdCartUser(cartUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}