package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AccountUser;

public interface AccountUserDAO {

    int addAccountUser(int idUer) throws DAOException;

    int getIdAccountUser(int idUser) throws DAOException;

    AccountUser getAccountUser(int idAccountUser) throws DAOException;
}
