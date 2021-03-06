package by.trjava.kaloshych.controller;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.factory.CommandFactory;
import by.trjava.kaloshych.command.factory.impl.CommandFactoryImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_COMMAND;
import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_PERMISSION;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_ERROR;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_MAIN_PAGE;


@WebServlet("/main")
public class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class);
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final boolean permission = (boolean) request.getAttribute(PARAMETER_PERMISSION);

        if (permission) {
            String commandName = request.getParameter(PARAMETER_COMMAND);
            logger.info("In DoGet " + commandName);
            try {
                final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
                final Command command = commandFactory.createCommand(commandName);
                final String commandResult = command.execute(request, response);

                request.getRequestDispatcher(commandResult).forward(request, response);
            } catch (CommandException e) {
                logger.error(e);
                response.sendRedirect(PATH_ERROR);
            }
        } else {
            response.sendRedirect(PATH_MAIN_PAGE);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final boolean permission = (boolean) request.getAttribute(PARAMETER_PERMISSION);

        if (permission) {
            String commandName = request.getParameter(PARAMETER_COMMAND);
            logger.info("In DoPost " + commandName);
            try {
                final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
                final Command command = commandFactory.createCommand(commandName);
                final String commandResult = command.execute(request, response);

                response.sendRedirect(commandResult);
            } catch (CommandException e) {
                logger.error(e);
                response.sendRedirect(PATH_ERROR);
            }
        } else {
            response.sendRedirect(PATH_MAIN_PAGE);
        }
    }

}