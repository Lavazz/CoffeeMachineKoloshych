package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.OrderDAO;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.CartUserService;
import by.trjava.kaloshych.service.exception.ServiceException;

/**
 * Represents methods for operation with CartUser Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see CartUser
 * @since JDK1.0
 */
public class CartUserServiceImpl implements CartUserService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final CartUserDAO cartUserDAO = daoFactory.getCartUserDAO();
    private final UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public CartUser addCartUserForAuthorization(User user) throws ServiceException {
        CartUser cartUser;
        final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            CartUser cartUserLast = cartUserDAO.getLastCartUser(user);
            Order orderLast = orderDAO.getLastOrderByUser(user.getId());
            if (!cartUserLast.equals(orderLast.getCartUser())) {
                cartUser = cartUserLast;
            } else {
                int idCartUser = cartUserDAO.addCartUser(user);
                cartUser = cartUserDAO.getCartUser(idCartUser);
            }
            return cartUser;
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't add cartUser" + e);
        }
    }

    @Override
    public CartUser addCartUser(int idUser) throws ServiceException {
        try {
            User user = userDAO.getUserById(idUser);
            int idCartUser = cartUserDAO.addCartUser(user);
            return cartUserDAO.getCartUser(idCartUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
