package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoSuchCommand implements Command {
    private static final Integer ERROR_NUMBER_500 = 500;
   private static final Logger logger = Logger.getLogger(NoSuchCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {

            logger.error("No such command error");
            response.sendError(ERROR_NUMBER_500);
        }

    }
