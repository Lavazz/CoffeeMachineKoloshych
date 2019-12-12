package by.trjava.kaloshych.service;

import by.trjava.kaloshych.service.exception.ServiceException;

public interface AccountUserService {

    int addAccountUser(int idUser) throws ServiceException;

    int getIdAccountUser(int idUser) throws ServiceException;

}
