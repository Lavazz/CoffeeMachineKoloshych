package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.*;
import by.trjava.kaloshych.service.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Message.*;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_INDEX;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_REGISTRATION;

public class RegistrationCommand implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private CartUserService cartUserService = ServiceFactory.getInstance().getCartUser();
    private AccountUserService accountUserService = ServiceFactory.getInstance().getAccountUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession(true);
        String path = PATH_REGISTRATION;
        String name = request.getParameter(PARAMETER_NAME);
        String email = request.getParameter(PARAMETER_EMAIL);
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String confirmPassword = request.getParameter(PARAMETER_CONFIRM_PASSWORD);

        try {
            final User user = userService.registration(login, password, confirmPassword, email, name);
            final AccountUser accountUser = accountUserService.createAccountUser(user);
            final CartUser cartUser = cartUserService.addCartUser(user.getId());
            session.setAttribute(PARAMETER_ID_ACCOUNT_USER, accountUser.getIdAccountUser());
            session.setAttribute(PARAMETER_ID_CART_USER, cartUser.getIdCartUser());
            session.setAttribute(PARAMETER_ID_USER, user.getId());
            session.setAttribute(PARAMETER_USER_NAME, user.getName());
            session.setAttribute(PARAMETER_ID_USER_STATUS, user.getUserStatus().getIdUserStatus());
            path = PATH_INDEX;
            session.setAttribute(REDIRECT_COMMAND, PATH_INDEX);
            session.setAttribute(PARAMETER_MAIN_MESSAGE, MESSAGE_SUCCESSFUL_REGISTRATION);
        } catch (EmptyDataException e) {
            session.setAttribute(PARAMETER_MESSAGE_REGISTRATION, MESSAGE_EMPTY_DATA);
        } catch (LoginUsedException e) {
            session.setAttribute(PARAMETER_MESSAGE_REGISTRATION, MESSAGE_LOGIN_USED);
        } catch (WrongConfirmPasswordException e) {
            session.setAttribute(PARAMETER_MESSAGE_REGISTRATION, MESSAGE_NOT_MATH);
        } catch (RegistrationException e) {
            session.setAttribute(PARAMETER_MESSAGE_REGISTRATION, MESSAGE_UNSUCCESSFUL_REGISTRATION);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return path;
    }
}
