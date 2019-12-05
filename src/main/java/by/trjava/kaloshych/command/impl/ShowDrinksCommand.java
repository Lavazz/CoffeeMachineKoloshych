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

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_ADDITIONAL_INGREDIENTS;
import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_DRINKS;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_DRINKS;

public class ShowDrinksCommand implements Command {

    private final AdditionalIngredientService additionalIngredientService = ServiceFactory.getInstance().getAdditionalIngredientService();
    private final DrinkService drinkService = ServiceFactory.getInstance().getDrinkService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        List<Drink> drinks;
        List<AdditionalIngredient> additionalIngredients;
        try {

            drinks = drinkService.getAllDrinks();
            additionalIngredients = additionalIngredientService.getAllAdditionalIngredients();
            request.setAttribute(PARAMETER_DRINKS, drinks);
            request.setAttribute(PARAMETER_ADDITIONAL_INGREDIENTS, additionalIngredients);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_DRINKS;
    }

}

