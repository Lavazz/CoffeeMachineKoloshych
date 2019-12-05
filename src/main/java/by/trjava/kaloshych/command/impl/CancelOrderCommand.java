package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_SHOW_CART;

public class CancelOrderCommand implements Command {

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        final int idCartUser = (int) session.getAttribute(PARAMETER_ID_CART_USER);

        try {
            orderService.cancelOrder(idCartUser);
            request.setAttribute(PARAMETER_CARTS, null);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_SHOW_CART;
    }

}
