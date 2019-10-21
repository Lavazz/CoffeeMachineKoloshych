package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.entity.AdditionalIngredient;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.service.AdditionalIngredientService;
import by.trjava.coffeemachine.service.DrinkService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;
import by.trjava.coffeemachine.service.manager.LocaleManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelectionAllDrinksCommand implements Command {

    private static final Integer ERROR_NUMBER_500 = 500;

  //  private static final String REDIRECT_PAGE_URL = "http://localhost:8080/CoffeeMachine/Servlet?command=goToShowAllDrinksPageCommand";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Drink> drinkList = null;
        List<AdditionalIngredient> additionalIngredientList= null;
        try {
            drinkList=getDrink();
            additionalIngredientList = getAdditionalIngredient();
        } catch (ServiceException| DAOException e) {
            response.sendError(500);
            e.printStackTrace();
        }

        request.setAttribute("drinks", drinkList);
        request.setAttribute("additionalIngredients", additionalIngredientList);
        request.getRequestDispatcher("drinks").forward(request, response);

    }

    private static List<AdditionalIngredient> getAdditionalIngredient() throws ServiceException, DAOException {
        AdditionalIngredientService additionalIngredientService = ServiceFactory.getInstance().getAdditionalIngredientService();
           return additionalIngredientService.listAllAdditionalIngredient();
    }

    private static List<Drink> getDrink() throws ServiceException, DAOException {
        DrinkService drinkService = ServiceFactory.getInstance().getDrinkService();
            return drinkService.listAllDrink();
    }
}

//можно ли статик???????????????????77