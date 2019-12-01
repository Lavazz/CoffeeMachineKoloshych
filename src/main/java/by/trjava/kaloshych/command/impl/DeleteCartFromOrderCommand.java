package by.trjava.kaloshych.command.impl;


import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Message.MESSAGE_EMPTY_CHECKBOX;
import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_COMMAND_SHOW_CART;

public class DeleteCartFromOrderCommand implements Command {

  private final   CartService cartService=ServiceFactory.getInstance().getCartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws  CommandException {

       String idCart=request.getParameter(PARAMETER_ID_CART);

        try {
            cartService.deleteDrinkFromCart(idCart);
        } catch (EmptyDataException e) {
            request.setAttribute(PARAMETER_MESSAGE_DELETE, MESSAGE_EMPTY_CHECKBOX);
        } catch (ServiceException e) {
            throw  new CommandException(e);
        }

        return PATH_COMMAND_SHOW_CART;

    }
}
