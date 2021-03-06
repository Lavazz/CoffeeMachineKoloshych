package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.CartUser;

public interface CartUserDAO {

    int addCartUser(int idUser) throws DAOException;

    CartUser getLastCartUser(int idUser) throws DAOException;

    CartUser getCartUser(int idCartUser) throws DAOException;
}
