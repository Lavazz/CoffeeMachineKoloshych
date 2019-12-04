package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.*;
import by.trjava.kaloshych.service.*;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_CART;

public class ShowCartCommand implements Command {

    private final CartService cartService = ServiceFactory.getInstance().getCartService();
    private final CartAdditionalIngredientService cartAdditionalIngredientService = ServiceFactory.getInstance().getCartAdditionalIngredient();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
        session.removeAttribute(PARAMETER_CARTS);
        session.removeAttribute(PARAMETER_ORDERS);
        session.removeAttribute(PARAMETER_CART_ADDITIONAL_INGREDIENTS);

        final CartUser cartUser = (CartUser) session.getAttribute(PARAMETER_CART_USER);
        final User user = (User) session.getAttribute(PARAMETER_USER);
        System.out.println("cartUser="+cartUser);
        List<CartAdditionalIngredient> cartAdditionalIngredients;
        List<Cart> carts;
        double totalCost;
        try {
            carts = cartService.getAllCarts(cartUser);

            if (carts.isEmpty()) {
                request.setAttribute(PARAMETER_CARTS, null);
            } else {
                request.setAttribute(PARAMETER_CARTS, carts);
            }
            cartAdditionalIngredients = cartAdditionalIngredientService.getCartAdditionalIngredientsByUser(user);
            totalCost = cartService.getTotalCost(cartUser);
            request.setAttribute(PARAMETER_CART_ADDITIONAL_INGREDIENTS, cartAdditionalIngredients);
            request.setAttribute(PARAMETER_TOTAL_COST, totalCost);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_CART;
    }
}
