package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.util.CreatorFullURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_ORDER_HISTORY;

public class ShowPreviousPageCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession(true);
        String url = CreatorFullURL.create(request);

        String parameterFirstRowName = request.getParameter(PARAMETER_FIRST_ROW);
        String parameterLastRowName = request.getParameter(PARAMETER_LAST_ROW);
        String currentPageNumberParameterName = request.getParameter(PARAMETER_CURRENT_PAGE_NUMBER);

        int lastIndex = Integer.parseInt(session.getAttribute(parameterFirstRowName).toString());
        int firstIndex = lastIndex - ROWS_BY_PAGE;
        int currentPageNumber = Integer.parseInt(session.getAttribute(currentPageNumberParameterName).toString());

        if (currentPageNumber > FIRST_PAGE_NUMBER) {
            session.setAttribute(currentPageNumberParameterName, (--currentPageNumber));
            session.setAttribute(parameterFirstRowName, firstIndex);
            session.setAttribute(parameterLastRowName, lastIndex);
        }

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        return PATH_ORDER_HISTORY;
    }


}