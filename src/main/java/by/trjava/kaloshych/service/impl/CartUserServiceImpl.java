package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.CartUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.CartUserService;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public class CartUserServiceImpl implements CartUserService {

    private static final CartUserDAO cartUserDAO= DAOFactory.getInstance().getCartUserDAO();

    @Override
    public void deleteCartUser(User user) throws ServiceException {
        try {
            cartUserDAO.deleteCartUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CartUser addCartUser(User user) throws ServiceException {
        try {
            return cartUserDAO.addCartUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CartUser> getCartUser(User user) throws ServiceException {
        try {
            return cartUserDAO.getCartUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CartUser getCartUserById(int idCartUser) throws ServiceException {
        try {
            return cartUserDAO.getCartUserById(idCartUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
