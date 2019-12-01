package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.PaymentMethod;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.PaymentMethodService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_REPLENISH_BALANCE;

public class ShowReplenishBalanceFormCommand implements Command {

    private final PaymentMethodService paymentMethodService = ServiceFactory.getInstance().getPaymentMethodService();
    private final AccountService accountService = ServiceFactory.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();
        List<PaymentMethod> paymentMethods;
        AccountUser accountUser = (AccountUser) session.getAttribute(PARAMETER_ACCOUNT_USER);
        String MESSAGE_BALANCE = (String) request.getAttribute(PARAMETER_MESSAGE_ORDER);
        try {
            double balance = accountService.getBalance(accountUser);
            paymentMethods = paymentMethodService.getAllPaymentMethods();
            request.setAttribute(PARAMETER_BALANCE, balance);
            request.setAttribute(PARAMETER_PAYMENT_METHODS, paymentMethods);
            request.setAttribute(PARAMETER_MESSAGE_BALANCE, MESSAGE_BALANCE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_REPLENISH_BALANCE;
    }
}
