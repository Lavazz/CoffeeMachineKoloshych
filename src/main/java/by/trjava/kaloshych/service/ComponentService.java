package by.trjava.kaloshych.service;

import by.trjava.kaloshych.service.exception.ServiceException;

public interface ComponentService {

    void deleteComponent(String... idComponents) throws ServiceException;

}
