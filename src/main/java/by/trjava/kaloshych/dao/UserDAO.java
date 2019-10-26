package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.exception.WrongAuthorizationDataException;
import by.trjava.kaloshych.entity.OrderJournal;
import by.trjava.kaloshych.entity.User;

import java.util.List;

public interface UserDAO {

    boolean updateUserPassword(String login, String newPassword) throws DAOException;

   boolean removeUser(String login) throws DAOException;

    User authorization(String userLogin, String userPassword) throws DAOException, WrongAuthorizationDataException;

     boolean registration(String userLogin, String userPassword, String userEmail, String userName) throws DAOException;

    List<User> listAllUsers() throws DAOException;

    boolean checkId(int id) throws DAOException;

    User getUserByLogin(String login) throws DAOException;

    int getIdUserByLogin(String login) throws DAOException;

    int getIdUserByOrder(OrderJournal orderJournal) throws DAOException;
}
