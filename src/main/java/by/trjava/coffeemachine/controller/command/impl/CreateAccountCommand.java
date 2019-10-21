package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.service.AccountService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.trjava.coffeemachine.controller.command.JSPPageName.PAGE_PERSONAL_ACCOUNT;

public class CreateAccountCommand implements Command {
    private static final String PARAMETER_PAYMENT_METHOD = "paymentMethod";
    private static final String PARAMETER_AMOUNT_OF_MONEY = "amountOfMoney";
    private static final String PARAMETER_LOGIN = "login";
  //  private static final String PARAMETER_PASSWORD = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
     HttpSession session=request.getSession();
        System.out.println("CreateAccountCommand");
        final String userLogin=String.valueOf(session.getAttribute(PARAMETER_LOGIN));
        final String paymentMethod=request.getParameter(PARAMETER_PAYMENT_METHOD);
        final double amountOfMoney=Double.parseDouble(request.getParameter(PARAMETER_AMOUNT_OF_MONEY));

        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        AccountService accountService=serviceFactory.getAccountService();
//        double balance= accountService.createAccount(userLogin, paymentMethod, amountOfMoney);
//        System.out.println(balance);
//request.setAttribute("balance", balance);
request.getRequestDispatcher(PAGE_PERSONAL_ACCOUNT ).forward(request, response);
    }
}
