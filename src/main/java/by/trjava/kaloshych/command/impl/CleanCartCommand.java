package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.CartUserService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_CART_USER;
import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_USER;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_INDEX;


public class CleanCartCommand implements Command {

    private final CartUserService cartUserService = ServiceFactory.getInstance().getCartUser();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
      final User user = (User) session.getAttribute(PARAMETER_USER);

        session.removeAttribute(PARAMETER_CART_USER);

        try {
            session.setAttribute(PARAMETER_CART_USER, cartUserService.addCartUser(user));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_INDEX;
    }
}
