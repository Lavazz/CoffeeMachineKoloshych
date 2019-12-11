package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.Order;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    void addOrder(int idCartUser, int idAccountUser) throws ServiceException;

    List<Order> getAllOrdersByUser(int idUser) throws ServiceException;

    void cancelOrder(int idCartUser) throws ServiceException;

}
