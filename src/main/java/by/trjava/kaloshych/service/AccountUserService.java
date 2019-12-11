package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.exception.ServiceException;

public interface AccountUserService {

    AccountUser addAccountUser(User user) throws ServiceException;

    AccountUser getAccountUser(User user) throws ServiceException;
}
