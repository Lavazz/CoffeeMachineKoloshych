package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;

public interface AccountUserDAO {
   AccountUser createAccountUser(User user) throws DAOException;
   AccountUser getAccountUser(User user) throws DAOException;
}
