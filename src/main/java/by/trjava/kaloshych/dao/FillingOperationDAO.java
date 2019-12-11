package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Component;

import java.util.List;

public interface FillingOperationDAO {

    List<Component> getAllComponents() throws DAOException;

    void fillingOperation(int idComponent) throws DAOException;

    void addComponentToFillingOperation(int idComponent) throws DAOException;
}
