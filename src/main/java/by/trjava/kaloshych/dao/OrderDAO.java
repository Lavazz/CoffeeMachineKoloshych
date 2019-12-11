package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Order;

import java.util.List;

public interface OrderDAO {

    int addOrder(int idCartUser, double totalCost) throws DAOException;

    List<Order> getAllOrdersByUser(int idUser) throws DAOException;

    Order getOrder(int idOrder) throws DAOException;

    Order getLastOrderByUser(int idUser) throws DAOException;
}
