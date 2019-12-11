package by.trjava.kaloshych.command.impl.user;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.InvalidCurrentPasswordException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.WrongConfirmPasswordException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Message.*;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_CHANGE_PASSWORD;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_COMMAND_ADMIN_CABINET;

public class ChangePasswordCommand implements Command {

    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        String path = PATH_CHANGE_PASSWORD;

        final int idUser = (int) session.getAttribute(PARAMETER_ID_USER);

        String currentPassword = request.getParameter(PARAMETER_CURRENT_PASSWORD);
        String newPassword = request.getParameter(PARAMETER_NEW_PASSWORD);
        String confirmedPassword = request.getParameter(PARAMETER_CONFIRMED_NEW_PASSWORD);

        try {
            userService.updateUserPassword(idUser, currentPassword, newPassword, confirmedPassword);
            request.setAttribute(PARAMETER_MESSAGE_CHANGE_PASSWORD, MESSAGE_CHANGE_PASSWORD_SUCCESSFUL);
            path = request.getContextPath() + PATH_COMMAND_ADMIN_CABINET;
        } catch (EmptyDataException e) {
            session.setAttribute(PARAMETER_MESSAGE_CHANGE_PASSWORD, MESSAGE_EMPTY_DATA);
        } catch (InvalidCurrentPasswordException e) {
            session.setAttribute(PARAMETER_MESSAGE_CHANGE_PASSWORD, MESSAGE_CHANGE_PASSWORD_INVALID);
        } catch (WrongConfirmPasswordException e) {
            session.setAttribute(PARAMETER_MESSAGE_CHANGE_PASSWORD, MESSAGE_NOT_MATH);
        } catch (ServiceException e) {
            throw new CommandException();
        }

        return path;
    }
}

