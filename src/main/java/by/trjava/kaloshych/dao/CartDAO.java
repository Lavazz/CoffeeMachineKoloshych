package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.entity.User;

import java.util.List;

public interface CartDAO {
    Cart addDrinkToCart(CartUser cartUser, Drink drink, int quantity) throws DAOException;

    void deleteDrinkFromCart(Cart cart) throws DAOException;

    boolean changePortion (Cart cart, int newPortion) throws DAOException;
    List<Cart> getAllCarts(CartUser cartUser) throws DAOException;

    int getPortionByCart(Cart cart)  throws DAOException;
    Cart getCartById(int idCart) throws DAOException;
    List<Cart> getAllCartsByUser(User user) throws DAOException;



}
