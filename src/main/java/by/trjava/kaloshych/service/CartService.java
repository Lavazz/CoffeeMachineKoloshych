package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface CartService {

    Cart addDrinkToCart(int idCartUser, String idDrink, String portion) throws ServiceException;

    void deleteDrinkFromCart(String idCart) throws ServiceException;

    boolean changePortion(int idCart, String sign) throws ServiceException;

    List<Cart> getAllCarts(int idCartUser) throws ServiceException;

    double getTotalCost(int idCartUser) throws ServiceException;

    List<Cart> getAllCartsByUser(int idUser) throws ServiceException;
}
