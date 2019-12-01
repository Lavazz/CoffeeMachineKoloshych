package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.InsufficientPortionException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.WrongPortionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Message.MESSAGE_ADDITIONAL_INGREDIENT_INSUFFICIENT_PORTION;
import static by.trjava.kaloshych.configuration.Message.MESSAGE_WRONG_PORTION;
import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.*;

public class ChangePortionCommand implements Command {

    private final CartService cartService = ServiceFactory.getInstance().getCartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
        final String sign = request.getParameter(PARAMETER_SIGN);
        final String idCart = request.getParameter(PARAMETER_CART);
        try {
            cartService.changePortion(idCart, sign);
        } catch (WrongPortionException e) {
            session.setAttribute(PARAMETER_MESSAGE_CART, MESSAGE_WRONG_PORTION);
        } catch (InsufficientPortionException e) {
            request.setAttribute(PARAMETER_MESSAGE_CART, MESSAGE_ADDITIONAL_INGREDIENT_INSUFFICIENT_PORTION);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return  PATH_COMMAND_SHOW_CART;

    }
}
