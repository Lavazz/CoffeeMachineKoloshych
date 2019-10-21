package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.exception.WrongAuthorizationDataException;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.entity.User;

import java.util.List;

public interface UserDAO {

    boolean updateUserPassword(String login, String newPassword) throws DAOException;

   boolean removeUser(String login);

    User authorization(String userLogin, String userPassword) throws DAOException, WrongAuthorizationDataException;

     boolean registration(String userLogin, String userPassword, String userEmail, String userName) throws DAOException;

    List<User> listAllUsers() throws DAOException;

    boolean checkId(int id);

    User getUserByLogin(String login) throws DAOException;

    int getIdUserByLogin(String login);

    int getIdUserByOrder(OrderJournal orderJournal);
}
