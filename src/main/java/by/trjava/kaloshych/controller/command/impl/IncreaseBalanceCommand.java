package by.trjava.kaloshych.controller.command.impl;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.AccountService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class IncreaseBalanceCommand implements Command {

    private static final String PARAMETER_PAYMENT_METHOD = "paymentMethod";
    private static final String PARAMETER_AMOUNT_OF_MONEY = "amountOfMoney";
    private static final String PARAMETER_LOGIN = "login";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, SQLException {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        final int idUser=user.getId();
        final String paymentMethod=request.getParameter(PARAMETER_PAYMENT_METHOD);
        System.out.println("paymentMethod"+paymentMethod);
        final double amountOfMoney=Double.parseDouble(request.getParameter(PARAMETER_AMOUNT_OF_MONEY));

        AccountService accountService= ServiceFactory.getInstance().getAccountService();
       double balance= accountService.increaseBalance(idUser, paymentMethod, amountOfMoney);
        request.setAttribute("balance", balance);
        request.getRequestDispatcher("/personalCabinet").forward(request, response);
    }
}
