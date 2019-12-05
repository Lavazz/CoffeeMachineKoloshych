package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
  void addOrder(int idCartUser) throws ServiceException;
    Date getDateOrderByIdCartUser(CartUser cartUser) throws ServiceException;
    double getTotalCostByIdCartUser(CartUser cartUser) throws ServiceException;
    void addOrderWithAdditionalIngredient(CartUser cartUser) throws ServiceException;
  List<Order> getAllOrdersByUser(int idUser) throws ServiceException;
 boolean checkIdOrder(int idOrder) throws ServiceException;
void cancelOrder(int idCartUser) throws ServiceException;

}
