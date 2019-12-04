package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.dao.exception.WrongLoginDAOException;
import by.trjava.kaloshych.entity.User;

import java.util.List;

public interface UserDAO {

    boolean updateUserPassword(User user, String newPassword) throws DAOException;

    User logIn(String userLogin, String userPassword) throws DAOException, WrongAuthorizationDataException;

     User registration(String userLogin, String userPassword, String userEmail, String userName) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    boolean checkId(int id) throws DAOException;
 String getPassword(int idUser) throws DAOException;
    User getUserByLogin(String login) throws DAOException;
    boolean checkUserExists(String login) throws DAOException;

    int getIdUserByLogin(String login) throws DAOException, WrongLoginDAOException;

    int getIdUserByOrder(int idOrder) throws DAOException;

    User getUserById(int idUser) throws DAOException;
}
