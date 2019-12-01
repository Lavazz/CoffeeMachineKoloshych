package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.*;
import by.trjava.kaloshych.service.*;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_PERSONAL_CABINET;


public class PersonalCabinetCommand implements Command {

    private final AccountService accountService = ServiceFactory.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        final AccountUser accountUser = (AccountUser) session.getAttribute(PARAMETER_ACCOUNT_USER);
        double balance;

        try {
            balance = accountService.getBalance(accountUser);
            request.setAttribute(PARAMETER_BALANCE, balance);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_PERSONAL_CABINET;
    }
}
