package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_SESSION_LOCALE;
import static by.trjava.kaloshych.configuration.PathToJSP.*;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String locale = request.getParameter(PARAMETER_SESSION_LOCALE);
        session.invalidate();
        HttpSession newSession=request.getSession(true);
        newSession.setAttribute(PARAMETER_SESSION_LOCALE, locale);

        return PATH_MAIN_PAGE;
    }

}
