package by.trjava.coffeemachine.service;

import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    public boolean updateUserPassword(String login, String newPassword) throws ServiceException;

    public boolean removeUser(String login) throws ServiceException;

    public User authorization(String userLogin, String userPassword) throws ServiceException;

    public boolean registration(String userLogin, String userPassword, String userEmail, String userName) throws ServiceException;

    public List<User> listAllUsers() throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
    boolean checkId(int id);
}
