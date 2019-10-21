package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static final String PARAMETER_LOCALE = "locale";
    private static final String SESSION_ATTRIBUTE_LOCAL = "locale";
    private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String newLocale = request.getParameter(PARAMETER_LOCALE);
        System.out.println(newLocale);
        session.setAttribute(SESSION_ATTRIBUTE_LOCAL, newLocale);

        //String url = (String) request.getSession(false).getAttribute(PARAMETER_PREVIOUS_REQUEST);
        String url = request.getHeader("referer");
        response.sendRedirect(url);

    }


}
