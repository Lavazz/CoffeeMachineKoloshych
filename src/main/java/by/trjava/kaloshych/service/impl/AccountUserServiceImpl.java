package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Account;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.AccountUserService;
import by.trjava.kaloshych.service.exception.ServiceException;

public class AccountUserServiceImpl implements AccountUserService {

    private  final  AccountUserDAO accountUserDAO= DAOFactory.getInstance().getAccountUserDAO();
    private  final AccountDAO accountDAO=DAOFactory.getInstance().getAccountDAO();

    @Override
    public AccountUser addAccountUser(User user) throws ServiceException {
        try {
           int idAccountUser=accountUserDAO.addAccountUser(user);
           AccountUser accountUser=accountUserDAO.getAccountUser(idAccountUser);
            accountDAO.addNewAccount(accountUser);
            return accountUser;
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in accountUserService can't add accountUser" + e);
        }
    }

    @Override
    public AccountUser getAccountUser(User user) throws ServiceException {
        try {
            return accountUserDAO.getAccountUser(user);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in accountUserService can't get accountUser" + e);
        }
    }
}
