package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    public boolean updateUserPassword(String login, String newPassword) throws ServiceException;

    public boolean removeUser(String login) throws ServiceException;

    public User authorization(String userLogin, String userPassword) throws ServiceException;

    public boolean registration(String userLogin, String userPassword, String userEmail, String userName) throws ServiceException;

    public List<User> listAllUsers() throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
    boolean checkId(int id) throws ServiceException;
}
