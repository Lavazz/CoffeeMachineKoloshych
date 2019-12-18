package by.trjava.kaloshych.command.impl.user;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_MAIN_PAGE;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        session.removeAttribute(PARAMETER_ID_USER);
        session.removeAttribute(PARAMETER_ID_USER_STATUS);
        session.removeAttribute(PARAMETER_ID_ACCOUNT_USER);
        session.removeAttribute(PARAMETER_ID_CART_USER);

        return PATH_MAIN_PAGE;
    }

}
