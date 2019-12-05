package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.*;

public class ChangeLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);

        String newLocale = request.getParameter(PARAMETER_SESSION_LOCALE);
        session.setAttribute(PARAMETER_SESSION_LOCALE, newLocale);

        return request.getHeader(PARAMETER_REFERER);
    }

}
