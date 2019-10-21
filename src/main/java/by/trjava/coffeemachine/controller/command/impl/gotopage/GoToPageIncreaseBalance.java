package by.trjava.coffeemachine.controller.command.impl.gotopage;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.controller.command.CreatorFullURL;
import by.trjava.coffeemachine.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToPageIncreaseBalance implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session;

        String url = CreatorFullURL.create(request);

        session = request.getSession(true);

       // session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

        RequestDispatcher dispatcher = request.getRequestDispatcher("registration");
        dispatcher.forward(request, response);

    }
}
