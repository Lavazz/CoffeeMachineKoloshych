package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_ADDITIONAL_INGREDIENTS;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_SHOW_ADDITIONAL_INGREDIENTS;

public class ShowAdditionalIngredientsCommand implements Command {

    private final AdditionalIngredientService additionalIngredientService = ServiceFactory.getInstance().getAdditionalIngredientService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        List<AdditionalIngredient> additionalIngredients;

        try {
            additionalIngredients = additionalIngredientService.getAllAdditionalIngredients();
            request.setAttribute(PARAMETER_ADDITIONAL_INGREDIENTS, additionalIngredients);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_SHOW_ADDITIONAL_INGREDIENTS;
    }

}

