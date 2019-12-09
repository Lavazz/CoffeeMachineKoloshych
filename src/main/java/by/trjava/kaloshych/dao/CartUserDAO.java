package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;

import java.sql.ResultSet;
import java.util.List;

public interface CartUserDAO {
    void deleteCartUser(User user) throws DAOException;
   int addCartUser(User user) throws DAOException;
    CartUser getLastCartUser(User user) throws DAOException;
    List<CartUser> getCartUser(User user) throws DAOException;
    CartUser getCartUserById(int idCartUser) throws DAOException;
}
