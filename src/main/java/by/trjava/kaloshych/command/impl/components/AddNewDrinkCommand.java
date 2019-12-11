package by.trjava.kaloshych.command.impl.components;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.DrinkService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.DuplicateComponentException;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.IncorrectComponentInformationException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.trjava.kaloshych.command.configuration.Message.*;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_ADD_NEW_DRINK;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_COMMAND_ADMIN_CABINET;


public class AddNewDrinkCommand implements Command {

    private final DrinkService drinkService = ServiceFactory.getInstance().getDrinkService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        String nameComponent = request.getParameter(PARAMETER_NAME_COMPONENT);
        String price = request.getParameter(PARAMETER_PRICE);
        String description = request.getParameter(PARAMETER_DESCRIPTION);
        String path = PATH_ADD_NEW_DRINK;

        try {
            drinkService.addNewDrink(nameComponent, price, description);
            path = request.getContextPath() + PATH_COMMAND_ADMIN_CABINET;
        } catch (EmptyDataException e) {
            session.setAttribute(PARAMETER_MESSAGE_ADD_DRINK, MESSAGE_EMPTY_DATA);
        } catch (IncorrectComponentInformationException e) {
            session.setAttribute(PARAMETER_MESSAGE_ADD_DRINK, MESSAGE_INCORRECT_DATA);
        } catch (DuplicateComponentException e) {
            session.setAttribute(PARAMETER_MESSAGE_ADD_DRINK, MESSAGE_DUPLICATE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return path;
    }
}
