package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.*;
import by.trjava.kaloshych.service.util.encrypting.PasswordEncrypting;
import by.trjava.kaloshych.service.validation.InputDataValidator;
import by.trjava.kaloshych.service.validation.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private  final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private  final InputDataValidator inputDataValidator = InputDataValidator.getInstance();
    private  final PasswordEncrypting encrypting= PasswordEncrypting.getInstance();

    @Override
    public boolean updateUserPassword(int idUser, String currentPassword, String newPassword, String confirmedPassword)
            throws ServiceException {
        String originalPassword;
        User user;
        if (inputDataValidator.isEmpty(confirmedPassword)
                || inputDataValidator.isEmpty(newPassword)
                || inputDataValidator.isEmpty(confirmedPassword)) {
            throw new EmptyDataException("Empty data");
        }
        if (!UserValidator.getInstance().validatePasswords(newPassword, confirmedPassword)) {
            throw new WrongConfirmPasswordException("Passwords do not match");
        }

        try {
             user=userDAO.getUserById(idUser);
            originalPassword = user.getPassword();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        if (!originalPassword.equals(encrypting.generateHash(user.getLogin(), currentPassword))) {
            throw new InvalidCurrentPasswordException("Incorrect current password");
        }

        String encodedPassword = encrypting.generateHash(user.getLogin(), newPassword);
        try {
            return userDAO.updateUserPassword(user, encodedPassword);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    public User logIn(String login, String password) throws ServiceException {
        String originalPassword;
        User user;
        if (inputDataValidator.isEmpty(password)
                || inputDataValidator.isEmpty(login)) {
            throw new EmptyDataException("Empty data");
        }
        if (!UserValidator.getInstance().validate(login, password)) {
            throw new WrongAuthorizationException("Incorrect authorization data");
        }
        try {
           if(userDAO.checkUserExists(login)) {
               user = userDAO.getUserByLogin(login);
           }else {
               throw new InvalidLoginException("The user with such login is not found");
           }
            originalPassword = user.getPassword();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        if (!originalPassword.equals(encrypting.generateHash(login, password))) {
            throw new InvalidCurrentPasswordException("Incorrect  password");
        }

            return user;
    }

    @Override
    public User registration(String login, String password, String confirmedPassword, String email, String name)
            throws ServiceException {
        if (inputDataValidator.isEmpty(login) || inputDataValidator.isEmpty(password)
                || inputDataValidator.isEmpty(confirmedPassword) || inputDataValidator.isEmpty(email)
                || inputDataValidator.isEmpty(name)) {
            throw new EmptyDataException("Empty data");
        }
        if (!UserValidator.getInstance().validate(login, password, email, name)) {
            throw new RegistrationException("Incorrect registration data");
        }
        if (!UserValidator.getInstance().validatePasswords(password, confirmedPassword)) {
            throw new WrongConfirmPasswordException("Passwords do not match");
        }
        if (checkUserExists(login)) {
            System.out.println("This login is used");
            throw new LoginUsedException("This login is used");
        }

        String encodedPassword = encrypting.generateHash(login, password);
        try {
            return userDAO.registration(login, encodedPassword, email, name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkId(int id) throws ServiceException {
        try {
            return userDAO.checkId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(int idUser) throws ServiceException {
        try {
            return userDAO.getUserById(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkUserExists(String login) throws ServiceException {
        try {
            return userDAO.checkUserExists(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            return userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
