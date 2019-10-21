package by.trjava.coffeemachine.service.impl;

import by.trjava.coffeemachine.dao.DAOFactory;
import by.trjava.coffeemachine.dao.UserDAO;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.exception.WrongAuthorizationDataException;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.UserService;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.validation.UserValidator;

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
        return userDAO.removeUser(login);
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
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkId(int id) {
        return userDAO.checkId(id);
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
