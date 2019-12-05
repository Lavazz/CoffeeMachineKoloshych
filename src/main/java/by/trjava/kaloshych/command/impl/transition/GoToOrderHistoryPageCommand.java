package by.trjava.kaloshych.command.impl.transition;


import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.util.CreatorFullURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_PREVIOUS_REQUEST;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_ORDER_HISTORY;

public class GoToOrderHistoryPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession(true);

        String url = CreatorFullURL.create(request);

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        return PATH_ORDER_HISTORY;

    }


}