package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;
import by.trjava.coffeemachine.controller.command.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.trjava.coffeemachine.controller.command.JSPPageName.PAGE_DRINK;

public class LogOutCommand implements Command {

        private static final String PARAMETER_USER = "user";
        private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            request.getSession().removeAttribute(PARAMETER_USER);

            response.sendRedirect(request.getContextPath() + "/index");

        }

    }
