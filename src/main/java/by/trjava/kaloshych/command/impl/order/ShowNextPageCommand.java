package by.trjava.kaloshych.command.impl.order;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.util.CreatorFullURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_ORDER_HISTORY;

public class ShowNextPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        final HttpSession session = request.getSession();
        String url = CreatorFullURL.create(request);

        String parameterFirstRowName = request.getParameter(PARAMETER_FIRST_ROW);
        String parameterLastRowName = request.getParameter(PARAMETER_LAST_ROW);
        String currentPageNumberParameterName = request.getParameter(PARAMETER_CURRENT_PAGE_NUMBER);
        String numberOfPagesParameterName = request.getParameter(PARAMETER_NUMBER_OF_PAGES);

        int firstIndex = Integer.parseInt(session.getAttribute(parameterLastRowName).toString());
        int lastIndex = firstIndex + ROWS_BY_PAGE;
        int numberOfPages = Integer.parseInt(session.getAttribute(numberOfPagesParameterName).toString());
        int currentPageNumber = Integer.parseInt(session.getAttribute(currentPageNumberParameterName).toString());

        if (numberOfPages > currentPageNumber) {
            session.setAttribute(currentPageNumberParameterName, (++currentPageNumber));
            session.setAttribute(parameterFirstRowName, firstIndex);
            session.setAttribute(parameterLastRowName, lastIndex);
        }

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        return PATH_ORDER_HISTORY;

    }


}