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
            int idOrder = orderDAO.addOrder(cartUser, totalCost);
            Order order=orderDAO.getOrder(idOrder);
            accountDAO.decreaseBalance(order);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in OrderService can't add order" + e);
        }
    }


    @Override
    public List<Order> getAllOrdersByUser(int idUser) throws ServiceException {
        try {
            User user = userDAO.getUserById(idUser);
            return orderDAO.getAllOrdersByUser(user);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in OrderService can't get all orders" + e);
        }
    }

    @Override
    public boolean checkIdOrder(int idOrder) throws ServiceException {
        try {
            return orderDAO.checkIdOrder(idOrder);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in OrderService can't check order" + e);
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
            throw new ServiceException("DAO Exception in OrderService can't cancel order" + e);
        }
    }

}
