package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.validation.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public boolean updateUserPassword(String login, String newPassword) throws ServiceException {
        if (!UserValidator.getInstance().validate(login, newPassword)) {
            throw new ServiceException("Incorrect update data");
       }
        try {
            return userDAO.updateUserPassword(login, newPassword);
        } catch ( DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean removeUser(String login) throws ServiceException {
        if (!UserValidator.getInstance().validateLogin(login)) {
            throw new ServiceException("There is no user with such data");
        }
        try {
            return userDAO.removeUser(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
//        if (!UserValidator.getInstance().validate(login, password)) {
//            throw new ServiceException("Incorrect authorization data");
//        }
        try {
            return userDAO.authorization(login, password);
        } catch (DAOException | WrongAuthorizationDataException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean registration(String login, String password, String email, String name) throws ServiceException {
//        if (!UserValidator.getInstance().validate( login, password,  email,  name)) {
//            throw new ServiceException("Incorrect registration data");
//        }
        try {
            return userDAO.registration(login, password,  email,  name);

        } catch ( DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> listAllUsers() throws ServiceException {
        try {
            return userDAO.listAllUsers();
        } catch ( DAOException e) {
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
    public User getUserByLogin(String login) throws ServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.getUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
