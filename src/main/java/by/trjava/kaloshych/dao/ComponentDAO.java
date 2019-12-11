package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;

public interface ComponentDAO {

    void deleteComponent(int idComponent) throws DAOException;

    void deleteComponentFromFillingOperation(int idComponent) throws DAOException;

}
