package by.trjava.kaloshych.command.impl.cart;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;
import by.trjava.kaloshych.service.CartAdditionalIngredientService;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_SHOW_CART;

public class ShowCartCommand implements Command {

    private final CartService cartService = ServiceFactory.getInstance().getCartService();
    private final CartAdditionalIngredientService cartAdditionalIngredientService = ServiceFactory.getInstance().getCartAdditionalIngredient();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
        session.removeAttribute(PARAMETER_CARTS);
        session.removeAttribute(PARAMETER_ORDERS);
        session.removeAttribute(PARAMETER_CART_ADDITIONAL_INGREDIENTS);

        final int idCartUser = (int) session.getAttribute(PARAMETER_ID_CART_USER);
        final int idUser = (int) session.getAttribute(PARAMETER_ID_USER);
        List<CartAdditionalIngredient> cartAdditionalIngredients;
        List<Cart> carts;
        double totalCost;
        try {
            carts = cartService.getAllCarts(idCartUser);
            request.setAttribute(PARAMETER_CARTS, carts);
            cartAdditionalIngredients = cartAdditionalIngredientService.getCartAdditionalIngredientsByUser(idUser);
            totalCost = cartService.getTotalCost(idCartUser);
            request.setAttribute(PARAMETER_CART_ADDITIONAL_INGREDIENTS, cartAdditionalIngredients);
            request.setAttribute(PARAMETER_TOTAL_COST, totalCost);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_SHOW_CART;
    }
}
