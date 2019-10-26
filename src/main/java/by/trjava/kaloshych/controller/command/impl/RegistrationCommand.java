package by.trjava.kaloshych.controller.command.impl;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.controller.command.CreatorFullURL;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
    private static final String PARAMETER_USER = "user";
    private static final Integer ERROR_NUMBER_500 = 500;

    private static final String SUCCESSFUL_REDIRECT_PAGE_URL = "http://localhost:8080/coffeeMachine/Servlet?command=goToShowAllDrinksPageCommand";
    private static final String UNSUCCESSFUL_REDIRECT_PAGE_URL = "http://localhost:8080/coffeeMachine/Servlet?command=goToRegistrationPage&wrong_params=true";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirectPage = UNSUCCESSFUL_REDIRECT_PAGE_URL;
        String name = request.getParameter(PARAMETER_NAME);
        String email = request.getParameter(PARAMETER_EMAIL);
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String url = CreatorFullURL.create(request);
        System.out.println(name);

        HttpSession session = request.getSession(true);

        UserService userService = ServiceFactory.getInstance().getUserService();


        User user = null;

        try {
            if(userService.registration(email, login, password, name)){
                user = userService.authorization(login, password);
                redirectPage = SUCCESSFUL_REDIRECT_PAGE_URL;
            }
        } catch (ServiceException e) {
            response.sendError(ERROR_NUMBER_500);
        }

        session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
        session.setAttribute(PARAMETER_USER, user);

       // response.sendRedirect(redirectPage);
        request.getRequestDispatcher("personalCabinet").forward(request, response);

    }
}
