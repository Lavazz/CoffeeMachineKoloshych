package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.service.CartAdditionalIngredientService;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.InsufficientPortionException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.WrongPortionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Message.*;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.dao.impl.configuration.ConfigurationManager.PARAMETER_PORTION;


public class AddToCartCommand implements Command {

    private final CartService cartService = ServiceFactory.getInstance().getCartService();
    private final CartAdditionalIngredientService cartAdditionalIngredientService = ServiceFactory.getInstance().getCartAdditionalIngredient();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        Cart cart = null;
        final int idCartUser = (int) session.getAttribute(PARAMETER_ID_CART_USER);

        String idDrink = request.getParameter(PARAMETER_DRINK_RADIO_BUTTON);
        String portion = request.getParameter(PARAMETER_PORTION);
        String[] additionalIngredients = request.getParameterValues(PARAMETER_ID_ADDITIONAL_INGREDIENT);

        try {

            try {
                cart = cartService.addDrinkToCart(idCartUser, idDrink, portion);
            } catch (EmptyDataException e) {
                session.setAttribute(PARAMETER_MESSAGE_DRINKS, MESSAGE_EMPTY_DATA);
            } catch (WrongPortionException e) {
                session.setAttribute(PARAMETER_MESSAGE_DRINKS, MESSAGE_WRONG_PORTION);
            } catch (InsufficientPortionException e) {
                session.setAttribute(PARAMETER_MESSAGE_DRINKS, MESSAGE_DRINK_INSUFFICIENT_PORTION);
            }

            try {
                if (cart != null) {
                    if (additionalIngredients != null) {
                        for (String idAdditionalIngredient : additionalIngredients) {
                            cartAdditionalIngredientService.addAdditionalIngredientToCartAI(cart, Integer.parseInt(idAdditionalIngredient));
                        }
                    }
                }
            }catch (InsufficientPortionException e) {
                request.setAttribute(PARAMETER_MESSAGE_CART, MESSAGE_ADDITIONAL_INGREDIENT_INSUFFICIENT_PORTION);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return request.getHeader(PARAMETER_REFERER);
    }
}
