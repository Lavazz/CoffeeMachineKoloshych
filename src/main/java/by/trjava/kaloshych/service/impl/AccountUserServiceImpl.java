package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.AccountDAO;
import by.trjava.kaloshych.dao.AccountUserDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.service.AccountUserService;
import by.trjava.kaloshych.service.exception.ServiceException;

/**
 * Represents methods for operation with AccountUser Entity in Service.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see AccountUser
 * @since JDK1.0
 */
public class AccountUserServiceImpl implements AccountUserService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final AccountUserDAO accountUserDAO = daoFactory.getAccountUserDAO();
    private final AccountDAO accountDAO = daoFactory.getAccountDAO();

    @Override
    public int addAccountUser(int idUser) throws ServiceException {
        try {
            int idAccountUser = accountUserDAO.addAccountUser(idUser);
            accountDAO.addNewAccount(idAccountUser);
            return idAccountUser;
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in accountUserService can't add accountUser" + e);
        }
    }

    @Override
    public int getIdAccountUser(int idUser) throws ServiceException {
        try {
            return accountUserDAO.getIdAccountUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception in accountUserService can't get accountUser" + e);
        }
    }
}
