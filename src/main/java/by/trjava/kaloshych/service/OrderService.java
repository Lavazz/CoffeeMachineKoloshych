package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;

public interface OrderService {
    int addOrder(int idUser) throws DAOException;
}
