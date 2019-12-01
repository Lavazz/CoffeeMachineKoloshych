package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.AdditionalIngredientService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.configuration.Message.MESSAGE_EMPTY_DATA;
import static by.trjava.kaloshych.configuration.Message.MESSAGE_INCORRECT_DATA;
import static by.trjava.kaloshych.configuration.Parameter.*;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_COMMAND_ADMIN_CABINET;

public class AddNewAdditionalIngredientCommand implements Command {

    private final AdditionalIngredientService additionalIngredientService = ServiceFactory.getInstance().getAdditionalIngredientService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String additionalIngredientName = request.getParameter(PARAMETER_NAME_COMPONENT);
        String calories = request.getParameter(PARAMETER_CALORIES);

        try {
            additionalIngredientService.addNewAdditionalIngredient(additionalIngredientName, calories);
        } catch (EmptyDataException e) {
            request.setAttribute(PARAMETER_MESSAGE_ADD_AI, MESSAGE_EMPTY_DATA);
        } catch (IncorrectComponentInformationException e) {
            request.setAttribute(PARAMETER_MESSAGE_ADD_AI, MESSAGE_INCORRECT_DATA);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return request.getContextPath() + PATH_COMMAND_ADMIN_CABINET;
    }
}
