package by.trjava.kaloshych.controller.command.impl.gotopage;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.controller.command.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToIndexPageCommand implements Command {
    private static final String TARGET_PAGE = "/WEB-INF/index.jsp";
    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;

        String url = CreatorFullURL.create(request);

        session = request.getSession(true);

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
        dispatcher.forward(request, response);
    }


}