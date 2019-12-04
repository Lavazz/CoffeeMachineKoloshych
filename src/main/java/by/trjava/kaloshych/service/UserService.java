package by.trjava.kaloshych.service;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.LoginUsedException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.WrongAuthorizationException;
import by.trjava.kaloshych.service.exception.WrongConfirmPasswordException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
   boolean updateUserPassword(int idUser, String currentPassword, String newPassword, String confirmedPassword) throws ServiceException;

    User logIn(String userLogin, String userPassword) throws ServiceException;

  User registration(String userLogin, String userPassword, String confirmPassword, String userEmail, String userName)
          throws ServiceException;

    List<User> getAllUsers() throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
    boolean checkId(int id) throws ServiceException;
    User getUserById(int idUser) throws ServiceException;
    boolean checkUserExists(String login) throws ServiceException;
}
