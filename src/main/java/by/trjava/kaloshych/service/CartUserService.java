package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;


public interface CartUserService {

   int addCartUser(int idUser) throws ServiceException;

}
