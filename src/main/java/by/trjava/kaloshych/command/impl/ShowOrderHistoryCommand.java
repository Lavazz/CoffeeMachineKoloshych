package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.util.CreatorFullURL;
import by.trjava.kaloshych.entity.*;
import by.trjava.kaloshych.service.*;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_CART_ADDITIONAL_INGREDIENTS;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_ORDER_HISTORY;


public class ShowOrderHistoryCommand implements Command {

    private final CartService cartService = ServiceFactory.getInstance().getCartService();
    private final CartAdditionalIngredientService cartAdditionalIngredientService = ServiceFactory.getInstance().getCartAdditionalIngredient();
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
        final int idUser = (int) session.getAttribute(PARAMETER_ID_USER);
        String url = CreatorFullURL.create(request);

        int firstIndex = 0;
        int lastIndex = firstIndex + ROWS_BY_PAGE;

        List<Order> orders;
        List<Cart> carts;
        List<CartAdditionalIngredient> cartAdditionalIngredients;

        try {

            orders = orderService.getAllOrdersByUser(idUser);
            carts = cartService.getAllCartsByUser(idUser);
            cartAdditionalIngredients = cartAdditionalIngredientService.getCartAdditionalIngredientsByUser(idUser);
            session.setAttribute(PARAMETER_ORDERS_FIRST_ROW, firstIndex);
            session.setAttribute(PARAMETER_ORDERS_LAST_ROW, lastIndex);

            int numberOfPages = (int) Math.ceil(orders.size() / (double) ROWS_BY_PAGE);
            if (numberOfPages == 0) {
                numberOfPages = DEFAULT_NUMBER_OF_PAGE;
            }

            session.setAttribute(PARAMETER_ORDERS_NUMBER_OF_PAGES, numberOfPages);
            session.setAttribute(PARAMETER_ORDERS_CURRENT_PAGE_NUMBER, FIRST_PAGE_NUMBER);
            session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);

            session.setAttribute(PARAMETER_CARTS, carts);
            session.setAttribute(PARAMETER_ORDERS, orders);
            session.setAttribute(PARAMETER_CART_ADDITIONAL_INGREDIENTS, cartAdditionalIngredients);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_ORDER_HISTORY;

    }
}
