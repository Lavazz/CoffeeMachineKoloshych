package by.trjava.kaloshych.command.impl.transition;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_ADD_NEW_ADDITIONAL_INGREDIENT;

public class GoToAddNewAdditionalIngredientPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{

       return PATH_ADD_NEW_ADDITIONAL_INGREDIENT;
    }
}
