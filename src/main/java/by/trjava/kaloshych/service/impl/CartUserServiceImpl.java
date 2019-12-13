package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.OrderDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.service.CartUserService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.Optional;

/**
 * Represents methods for operation with CartUser Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see CartUser
 * @since JDK1.0
 */
public class CartUserServiceImpl implements CartUserService {

    private final CartUserDAO cartUserDAO = DAOFactory.getInstance().getCartUserDAO();

    @Override
    public int addCartUser(int idUser) throws ServiceException {
        int idCartUser;
        final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            Optional<CartUser> cartUserOptional = Optional.ofNullable(cartUserDAO.getLastCartUser(idUser));
            Optional<Order> orderOptional = Optional.ofNullable(orderDAO.getLastOrderByUser(idUser));
            if (cartUserOptional.isPresent() && orderOptional.isPresent()) {
                if (!cartUserOptional.get().equals(orderOptional.get().getCartUser())) {
                    idCartUser = cartUserOptional.get().getIdCartUser();
                } else {
                    idCartUser = cartUserDAO.addCartUser(idUser);
                }
            } else {
                idCartUser = cartUserDAO.addCartUser(idUser);
            }
            return idCartUser;
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't add cartUser" + e);
        }

    }

}
