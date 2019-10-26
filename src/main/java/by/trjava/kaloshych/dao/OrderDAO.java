package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;

public interface OrderDAO {
    int addOrder(int idUser) throws DAOException;
}
