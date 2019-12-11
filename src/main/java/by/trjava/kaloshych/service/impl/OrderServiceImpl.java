package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.InsufficientMoneyException;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

/**
 * Represents methods for operation with Order Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Order
 * @since JDK1.0
 */
public class OrderServiceImpl implements OrderService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final CartDAO cartDAO = daoFactory.getCartDAO();
    private final OrderDAO orderDAO = daoFactory.getOrderDAO();
    private final CartAdditionalIngredientDAO cartAdditionalIngredientDAO = daoFactory.getCartAdditionalIngredientDAO();
    private final AccountDAO accountDAO = daoFactory.getAccountDAO();

    @Override
    public void addOrder(int idCartUser, int idAccountUser) throws ServiceException {
        final CartService cartService = ServiceFactory.getInstance().getCartService();
        double totalCost;
        try {
            double currentBalance = accountDAO.getBalance(idAccountUser);
            totalCost = cartService.getTotalCost(idCartUser);
            if (currentBalance < totalCost) {
                throw new InsufficientMoneyException("In account insufficient money");
            }
            int idOrder = orderDAO.addOrder(idCartUser, totalCost);
            Order order = orderDAO.getOrder(idOrder);
            accountDAO.decreaseBalance(order, idAccountUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in OrderService can't add order" + e);
        }
    }


    @Override
    public List<Order> getAllOrdersByUser(int idUser) throws ServiceException {
        try {
            return orderDAO.getAllOrdersByUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in OrderService can't get all orders" + e);
        }
    }

    @Override
    public void cancelOrder(int idCartUser) throws ServiceException {
        try {
            List<Cart> carts = cartDAO.getAllCarts(idCartUser);
            for (Cart cart : carts) {
                List<CartAdditionalIngredient> cartAdditionalIngredients = cartAdditionalIngredientDAO.getAllCartAdditionalIngredientByCart(cart);
                cartDAO.deleteDrinkFromCart(cart.getIdCart());
                for (CartAdditionalIngredient cartAdditionalIngredient : cartAdditionalIngredients) {
                    cartAdditionalIngredientDAO.deleteAdditionalIngredientFromCartAI(cartAdditionalIngredient.getIdCartAdditionalIngredient());
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in OrderService can't cancel order" + e);
        }
    }

}
