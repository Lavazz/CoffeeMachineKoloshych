package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.OrderService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.trjava.coffeemachine.controller.command.impl.MakeOrderCommand.USER;

public class PersonalCabinetCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int idUser=user.getId();
        OrderService orderService= ServiceFactory.getInstance().getOrderService();
       // orderService.getUserOrder(idUser);

        request.getRequestDispatcher("personalCabinet").forward(request, response);
    }
}
