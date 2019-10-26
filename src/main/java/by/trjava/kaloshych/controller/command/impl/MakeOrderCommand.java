package by.trjava.kaloshych.controller.command.impl;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.OrderService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException {
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
        OrderService orderService= ServiceFactory.getInstance().getOrderService();

    int idOrder =orderService.addOrder(idUser);
        request.setAttribute("idOrder", idOrder);

        request.getRequestDispatcher("drinks").forward(request, response);
    }

}
