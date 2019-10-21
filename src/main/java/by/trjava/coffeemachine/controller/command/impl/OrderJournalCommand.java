package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.OrderJournal;
import by.trjava.coffeemachine.entity.User;
import by.trjava.coffeemachine.service.OrderJournalService;
import by.trjava.coffeemachine.service.OrderService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderJournalCommand implements Command {

    private static final String ID_USER = "idUser";
    private static final String ID_DRINK = "idDrink";
    private static final String ID_ADDITIONAL_INGREDIENT = "idAdditionalIngredient";
    private static final String DRINK_PORTION = "portion";
    public static final String USER = "user";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        int idUser=user.getId();
        List<OrderJournal> orderJournals= new ArrayList<>();
        int idOrder=0;

         int  idDrink = Integer.parseInt(request.getParameter("radioButtonDrink"));
         int idAdditionalIngredient = Integer.parseInt(request.getParameter("radioButtonIngredient"));
         int portion = Integer.parseInt(request.getParameter(DRINK_PORTION));

//orderJournals.add(new OrderJournal(idOrder, idDrink, idAdditionalIngredient, portion))

        OrderJournalService orderJournalService = ServiceFactory.getInstance().getOrderJournalService();
        OrderService orderService=ServiceFactory.getInstance().getOrderService();
        idOrder= orderService.addOrder(idUser);
        System.out.println("idOrder"+ idOrder);
        try {
            orderJournals.add(new OrderJournal(idOrder, idDrink, idAdditionalIngredient,portion));
            orderJournalService.addOrderInJournal(orderJournals);

        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        }
        //String balance = Double.toString(orderService.makeOrder(idUser, drink, additionalIngredient, portion));
        //request.setAttribute(balance, "balance");

        request.getRequestDispatcher("/WEB-INF/jsp/personalCabinet.jsp").forward(request, response);
    }

    private static int getIdOrder(int idUser) throws DAOException {
        OrderService orderService=ServiceFactory.getInstance().getOrderService();
      return orderService.addOrder(idUser);
    }
    }