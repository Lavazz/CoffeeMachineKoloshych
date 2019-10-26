package by.trjava.kaloshych.controller.command.impl;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Ingredient;
import by.trjava.kaloshych.service.FillingOperationService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FillingOperationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException {

            FillingOperationService fillingOperationService = ServiceFactory.getInstance().getFillingOperationService();
            List<Ingredient> ingredientList = fillingOperationService.getIngredient();


            request.setAttribute("ingredients", ingredientList);
            request.getRequestDispatcher("fillingOperation").forward(request, response);
        }
    }
