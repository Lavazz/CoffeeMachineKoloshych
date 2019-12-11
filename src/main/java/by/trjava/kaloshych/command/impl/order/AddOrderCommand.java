package by.trjava.kaloshych.command.impl.order;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.InsufficientMoneyException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Message.MESSAGE_INSUFFICIENT_MONEY;
import static by.trjava.kaloshych.command.configuration.Message.MESSAGE_SUCCESSFUL_ADD_ORDER;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_COMMAND_REPLENISH_BALANCE_FORM;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_TAKE_ORDER;

public class AddOrderCommand implements Command {

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        final int idCartUser = (int) session.getAttribute(PARAMETER_ID_CART_USER);
        final int idAccountUser = (int) session.getAttribute(PARAMETER_ID_ACCOUNT_USER);

        String path;

        try {
            orderService.addOrder(idCartUser, idAccountUser);
            request.setAttribute(PARAMETER_MESSAGE_ORDER, MESSAGE_SUCCESSFUL_ADD_ORDER);
            path = PATH_TAKE_ORDER;
        } catch (InsufficientMoneyException e) {
            session.setAttribute(PARAMETER_MESSAGE_REPLENISH, MESSAGE_INSUFFICIENT_MONEY);
            path = PATH_COMMAND_REPLENISH_BALANCE_FORM;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return path;
    }
}