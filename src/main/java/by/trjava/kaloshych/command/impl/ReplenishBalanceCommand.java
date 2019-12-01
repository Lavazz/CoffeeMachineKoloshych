package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.SmallAmountException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Message.*;
import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.*;

public class ReplenishBalanceCommand implements Command {

    private final AccountService accountService = ServiceFactory.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
        String path = PATH_COMMAND_PERSONAL_CABINET;

        final AccountUser accountUser = (AccountUser) session.getAttribute(PARAMETER_ACCOUNT_USER);
        String paymentMethod = request.getParameter(PARAMETER_RADIO_PAYMENT_METHOD);
        String amountOfMoney = request.getParameter(PARAMETER_AMOUNT_OF_MONEY);

        try {
            accountService.replenishBalance(accountUser, paymentMethod, amountOfMoney);
        } catch (EmptyDataException e) {
            request.setAttribute(PARAMETER_MESSAGE_REPLENISH, MESSAGE_EMPTY_DATA);
            path = PATH_COMMAND_REPLENISH_BALANCE;
        } catch (SmallAmountException e) {
            request.setAttribute(PARAMETER_MESSAGE_REPLENISH, MESSAGE_SMALL_AMOUNT);
            path = PATH_COMMAND_REPLENISH_BALANCE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return request.getContextPath() + path;
    }
}
