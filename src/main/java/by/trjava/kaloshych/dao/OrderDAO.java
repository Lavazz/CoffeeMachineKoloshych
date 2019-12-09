package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrderDAO {
    int addOrder(CartUser cartUser, double totalCost) throws DAOException;
    List<Order> getAllOrdersByUser(User user) throws DAOException;
    boolean checkIdOrder(int idOrder) throws DAOException;
    Order getOrder(int idOrder) throws DAOException;
   Order getLastOrderByUser(User user) throws  DAOException;
}
