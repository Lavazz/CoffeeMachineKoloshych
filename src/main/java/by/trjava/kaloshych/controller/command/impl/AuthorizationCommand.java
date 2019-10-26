package by.trjava.kaloshych.controller.command.impl;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.controller.command.CreatorFullURL;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {

    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_USER = "user";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_USER_STATUS = "status";
    private static final String PARAMETER_ID_ORDER = "idOrder";
    private static final String PARAMETER_WRONG_MESSAGE="wrongLoginMessage";
    private static final String WRONG_MESSAGE="Check please login and password";

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

            User user = userService.authorization(login, password);

            if (!user.equals(null)) {
                session.setAttribute(PARAMETER_USER, userService.getUserByLogin(login));
                session.setAttribute(PARAMETER_USER_STATUS, user.getStatus());
                OrderService order = ServiceFactory.getInstance().getOrderService();
                int idOrder = order.addOrder(user.getId());
                session.setAttribute(PARAMETER_ID_ORDER, idOrder);
                session.setMaxInactiveInterval(60*20);
                response.sendRedirect(request.getContextPath() + "/index");
            } else {
                request.setAttribute(PARAMETER_WRONG_MESSAGE, WRONG_MESSAGE);

                request.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
                request.getRequestDispatcher("/index").forward(request, response);
            }
        } catch (ServiceException e) {
            request.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
            request.getRequestDispatcher("/index").forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}