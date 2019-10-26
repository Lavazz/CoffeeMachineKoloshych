package by.trjava.kaloshych.controller.command.impl.gotopage;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.controller.command.CreatorFullURL;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToPageIncreaseBalanceCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session=request.getSession(true);

        String url = CreatorFullURL.create(request);

       // session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/increaseBalance");
        dispatcher.forward(request, response);

    }
}
