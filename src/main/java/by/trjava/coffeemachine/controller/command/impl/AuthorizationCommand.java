package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.controller.command.CreatorFullURL;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.UserService;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.manager.LocaleManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.trjava.coffeemachine.controller.command.JSPPageName.PAGE_DRINK;

public class AuthorizationCommand implements Command {

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_USER = "user";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
    private static final String PARAMETER_WRONG_PARAMS = "wrong_params";
    private static final String MESSAGE_SUCCESSFUL_AUTHORIZATION = "WELCOME!";
    private static final String MESSAGE_UNSUCCESSFUL_AUTHORIZATION = "Incorrect authorization data";

    private static final Integer ERROR_NUMBER_500 = 500;

    private static final String HOME_PAGE = "/WEB-INF/jsp/drinks.jsp";
    private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = CreatorFullURL.create(request);
        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession();
        String login = request.getParameter(PARAMETER_LOGIN).trim();
        String password = request.getParameter(PARAMETER_PASSWORD).trim();

        try {
            System.out.println("user in aut"+login+password);
         User user= userService.authorization(login, password);

            if(user!=null) {
                session.setAttribute(PARAMETER_USER, userService.getUserByLogin(login));
                request.setAttribute("message", "Welcome!");
                response.sendRedirect(request.getContextPath() + "/index");
            }else{
                request.setAttribute("message", "Check please login and password");
                request.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
                request.getRequestDispatcher("/index").forward(request, response);
            }
        } catch (ServiceException e) {
            System.out.println("Exs aut");
            request.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
            request.getRequestDispatcher("/index").forward(request, response);
        }
    }

}