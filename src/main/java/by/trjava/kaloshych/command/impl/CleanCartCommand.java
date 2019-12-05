package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.CartUserService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_INDEX;


public class CleanCartCommand implements Command {

    private final CartUserService cartUserService = ServiceFactory.getInstance().getCartUser();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
      final int idUser = (int) session.getAttribute(PARAMETER_ID_USER);

        session.removeAttribute(PARAMETER_ID_CART_USER);

        try {
            session.setAttribute(PARAMETER_ID_CART_USER, cartUserService.addCartUser(idUser).getIdCartUser());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_INDEX;
    }
}
