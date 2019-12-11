package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.*;
import by.trjava.kaloshych.service.util.encrypting.PasswordEncrypting;
import by.trjava.kaloshych.service.validation.InputDataValidator;
import by.trjava.kaloshych.service.validation.UserValidator;

/**
 * Represents methods for operation with User Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see User
 * @since JDK1.0
 */
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final InputDataValidator inputDataValidator = InputDataValidator.getInstance();
    private final PasswordEncrypting encrypting = PasswordEncrypting.getInstance();

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
            user = userDAO.getUserById(idUser);
            originalPassword = user.getPassword();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        if (!originalPassword.equals(encrypting.generateHash(user.getLogin(), currentPassword))) {
            throw new InvalidCurrentPasswordException("Incorrect current password");
        }

        String encodedPassword = encrypting.generateHash(user.getLogin(), newPassword);
        try {
            return userDAO.updateUserPassword(idUser, encodedPassword);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in UserService can't update password" + e);
        }
    }


    @Override
    public User authorization(String login, String password) throws ServiceException {
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
            if (userDAO.checkUserExists(login)) {
                user = userDAO.getUserByLogin(login);
            } else {
                throw new InvalidLoginException("The user with such login is not found");
            }
            originalPassword = user.getPassword();
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in UserService can't log in" + e);
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

        boolean userExists;
        try {
            userExists = userDAO.checkUserExists(login);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in UserService in exists" + e);
        }

        if (userExists) {
            throw new LoginUsedException("This login is used");
        }

        String encodedPassword = encrypting.generateHash(login, password);
        try {
            int idUser = userDAO.registration(login, encodedPassword, email, name);
            return userDAO.getUserById(idUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in UserService in registration" + e);
        }
    }


}
