package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.*;
import by.trjava.kaloshych.service.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Message.*;
import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_AUTHORIZATION;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_INDEX;

public class AuthorizationCommand implements Command {

    private UserService userService = ServiceFactory.getInstance().getUserService();
    private CartUserService cartUserService = ServiceFactory.getInstance().getCartUser();
    private AccountUserService accountUserService = ServiceFactory.getInstance().getAccountUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession(true);

        String login = request.getParameter(PARAMETER_LOGIN).trim();
        String password = request.getParameter(PARAMETER_PASSWORD).trim();

        String pagePath = PATH_AUTHORIZATION;
        try {
            User user = userService.logIn(login, password);
            CartUser cartUser = cartUserService.addCartUser(user);

            session.setAttribute(PARAMETER_CART_USER, cartUser);
            session.setAttribute(PARAMETER_ACCOUNT_USER, accountUserService.getAccountUser(user));
            session.setAttribute(PARAMETER_USER, user);
            session.setAttribute(PARAMETER_ID_USER, user.getId());
            session.setAttribute(PARAMETER_ID_USER_STATUS, user.getUserStatus().getIdUserStatus());
            session.setMaxInactiveInterval(60 * 20);

            pagePath = PATH_INDEX;

        } catch (EmptyDataException e) {
            session.setAttribute(PARAMETER_WRONG_MESSAGE, MESSAGE_EMPTY_DATA);
        } catch (InvalidLoginException e) {
            session.setAttribute(PARAMETER_WRONG_MESSAGE, MESSAGE_INVALID_LOGIN);
        } catch (InvalidCurrentPasswordException e) {
            session.setAttribute(PARAMETER_WRONG_MESSAGE, MESSAGE_INVALID_PASSWORD);
        } catch (WrongAuthorizationException e) {
            session.setAttribute(PARAMETER_WRONG_MESSAGE, MESSAGE_WRONG_AUTHORIZATION);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return pagePath;
    }

}