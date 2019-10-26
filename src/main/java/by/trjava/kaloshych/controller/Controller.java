package by.trjava.kaloshych.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.trjava.kaloshych.controller.command.Command;
import by.trjava.kaloshych.controller.command.CommandProvider;
import org.apache.log4j.Logger;


@WebServlet("/home")
public class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class);

    private static final Integer ERROR_NUMBER_500 = 500;

    private static final long serialVersionUID = 1L;

    private static final String PARAMETER_COMMAND = "command";

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("met get");
        doRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("met post");
        doRequest(request, response);

    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandName = request.getParameter(PARAMETER_COMMAND);
        CommandProvider provider = CommandProvider.getInstance();
        Command command = provider.getCommand(commandName);
       System.out.println(command.getClass().getName());
        try {
            command.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in Controller");
            response.sendError(ERROR_NUMBER_500);
        }
    }

}