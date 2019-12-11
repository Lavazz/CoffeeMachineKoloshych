package by.trjava.kaloshych.command.impl.user;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_BALANCE;
import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_ID_ACCOUNT_USER;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_PERSONAL_CABINET;


public class PersonalCabinetCommand implements Command {

    private final AccountService accountService = ServiceFactory.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        int idAccountUser = (int) session.getAttribute(PARAMETER_ID_ACCOUNT_USER);
        double balance;

        try {
            balance = accountService.getBalance(idAccountUser);
            request.setAttribute(PARAMETER_BALANCE, balance);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_PERSONAL_CABINET;
    }
}
