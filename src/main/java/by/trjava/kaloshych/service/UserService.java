package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

public interface UserService {

    boolean updateUserPassword(int idUser, String currentPassword, String newPassword, String confirmedPassword) throws ServiceException;

    User authorization(String userLogin, String userPassword) throws ServiceException;

    User registration(String userLogin, String userPassword, String confirmPassword, String userEmail, String userName)
            throws ServiceException;

}
