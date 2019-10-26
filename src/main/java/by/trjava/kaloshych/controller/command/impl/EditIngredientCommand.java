package by.trjava.kaloshych.controller.command.impl;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.AdditionalIngredientServiceException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditIngredientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException, SQLException {

        AdditionalIngredientService additionalIngredientService= ServiceFactory.getInstance().getAdditionalIngredientService();

        String nameAdditionalIngredient=request.getParameter("additionalIngredient");
        double price=Double.parseDouble(request.getParameter("price"));

        try {
            additionalIngredientService.addAdditionalIngredient(nameAdditionalIngredient, price);
        } catch (AdditionalIngredientServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("editIngredient").forward(request, response);
    }
}
