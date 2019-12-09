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

import java.util.List;

public class CartUserServiceImpl implements CartUserService {

    private  final CartUserDAO cartUserDAO= DAOFactory.getInstance().getCartUserDAO();
    private  final UserDAO userDAO= DAOFactory.getInstance().getUserDAO();

    @Override
    public void deleteCartUser(User user) throws ServiceException {
        try {
            cartUserDAO.deleteCartUser(user);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't delete cartUser" + e);
        }
    }

    @Override
    public CartUser addCartUserForAuthorization(User user) throws ServiceException {
        CartUser cartUser;
        final OrderDAO orderDAO=DAOFactory.getInstance().getOrderDAO();
        try {
            CartUser cartUserLast=cartUserDAO.getLastCartUser(user);
            Order orderLast=orderDAO.getLastOrderByUser(user);
            if(!cartUserLast.equals(orderLast.getCartUser())){
                cartUser=cartUserLast;
            }else {
                int idCartUser = cartUserDAO.addCartUser(user);
                cartUser=cartUserDAO.getCartUserById(idCartUser);
            }
            return cartUser;
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't add cartUser" + e);
        }
    }

    @Override
    public CartUser addCartUser(int idUser) throws ServiceException {
        try {
            User user=userDAO.getUserById(idUser);
            int idCartUser=cartUserDAO.addCartUser(user);
            return cartUserDAO.getCartUserById(idCartUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CartUser> getCartUser(User user) throws ServiceException {
        try {
            return cartUserDAO.getCartUser(user);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't get cartUser" + e);
        }
    }

    @Override
    public CartUser getLastCartUser(User user) throws ServiceException {
        try {
            return cartUserDAO.getLastCartUser(user);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't get last cartUser" + e);
        }
    }

    @Override
    public CartUser getCartUserById(int idCartUser) throws ServiceException {
        try {
            return cartUserDAO.getCartUserById(idCartUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in CartUserService can't get cartUser by id" + e);
        }
    }
}
