package by.trjava.kaloshych.command.impl.transition;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.trjava.kaloshych.configuration.PathToJSP.PATH_FILLING_OPERATION;

public class GoToFillingOperationPageCommand implements Command {
    @Override
    public String  execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        return PATH_FILLING_OPERATION;
        }
    }
