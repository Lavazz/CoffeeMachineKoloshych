package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_CARTS;
import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_CART_USER;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_SHOW_CART;

public class CancelOrderCommand implements Command {

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        final CartUser cartUser = (CartUser) session.getAttribute(PARAMETER_CART_USER);

        try {
            orderService.cancelOrder(cartUser);
            request.setAttribute(PARAMETER_CARTS, null);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_SHOW_CART;
    }

}
