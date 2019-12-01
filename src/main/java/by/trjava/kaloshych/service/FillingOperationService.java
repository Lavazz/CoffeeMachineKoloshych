package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface FillingOperationService {
    List<Component> getAllComponents() throws ServiceException;
    void fillingOperation(String ... idComponent) throws ServiceException;
}
