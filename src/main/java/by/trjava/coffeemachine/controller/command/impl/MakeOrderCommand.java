package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.OrderService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MakeOrderCommand implements Command {

    private static final String ID_USER = "idUser";
    private static final String ID_DRINK = "idDrink";
    private static final String ID_ADDITIONAL_INGREDIENT = "idAdditionalIngredient";
    private static final String DRINK_PORTION = "portion";
    public static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
          User user = (User) session.getAttribute(USER);
        //numderFormatException
         int idUser=user.getId();


        try {
            int drink = Integer.parseInt(request.getParameter(ID_DRINK));
        } catch (Exception e) {
            System.out.println("Exception 1");
            e.printStackTrace();
        }
        try {
            int additionalIngredient = Integer.parseInt(request.getParameter("radioButton"));
            System.out.println("additionalIngredient"+additionalIngredient);
        } catch (Exception e) {
            System.out.println("Exception2");
        }
        try {
            int portion = Integer.parseInt(request.getParameter(DRINK_PORTION));
        }catch (Exception e){
            System.out.println("Exception3");
            e.printStackTrace();
        }




        //String balance = Double.toString(orderService.makeOrder(idUser, drink, additionalIngredient, portion));
        //request.setAttribute(balance, "balance");

        request.getRequestDispatcher("/WEB-INF/jsp/personalCabinet.jsp").forward(request, response);
    }

}
