package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface CartUserService {
    void deleteCartUser(User user) throws ServiceException;
    CartUser addCartUser(User user) throws ServiceException;
    List<CartUser> getCartUser(User user) throws ServiceException ;
    CartUser getCartUserById(int idCartUser) throws ServiceException;
}
