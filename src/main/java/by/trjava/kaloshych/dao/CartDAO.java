package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;

import java.util.List;

public interface CartDAO {

    int addDrinkToCart(int idCartUser, int idDrink, int quantity) throws DAOException;

    void deleteDrinkFromCart(int idCart) throws DAOException;

    boolean changePortion(int idCart, int newPortion) throws DAOException;

    List<Cart> getAllCarts(int idCartUser) throws DAOException;

    int getPortionByCart(Cart cart) throws DAOException;

    Cart getCartById(int idCart) throws DAOException;

    List<Cart> getAllCartsByUser(int idUser) throws DAOException;


}
