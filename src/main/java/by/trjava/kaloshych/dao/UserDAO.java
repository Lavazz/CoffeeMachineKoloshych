package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.User;

public interface UserDAO {

    boolean updateUserPassword(int idUser, String newPassword) throws DAOException;

    int registration(String userLogin, String userPassword, String userEmail, String userName) throws DAOException;

    User getUserByLogin(String login) throws DAOException;

    boolean checkUserExists(String login) throws DAOException;

    User getUserById(int idUser) throws DAOException;
}
