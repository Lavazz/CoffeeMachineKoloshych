package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.DrinkService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_ADDITIONAL_INGREDIENTS;
import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_DRINKS;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_DRINKS;

public class ShowDrinksCommand implements Command {

    private final AdditionalIngredientService additionalIngredientService = ServiceFactory.getInstance().getAdditionalIngredientService();
    private final DrinkService drinkService = ServiceFactory.getInstance().getDrinkService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        List<Drink> drinks;
        List<AdditionalIngredient> additionalIngredients;
        try {

            drinks = drinkService.getAllDrinks();
            additionalIngredients = additionalIngredientService.getAllAdditionalIngredients();
            session.setAttribute(PARAMETER_DRINKS, drinks);
            session.setAttribute(PARAMETER_ADDITIONAL_INGREDIENTS, additionalIngredients);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_DRINKS;
    }

}

