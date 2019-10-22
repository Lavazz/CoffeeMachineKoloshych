package by.trjava.coffeemachine.controller.command.impl;

import by.trjava.coffeemachine.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static final String SESSION_ATTRIBUTE_LOCALE = "locale";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String newLocale = request.getParameter(SESSION_ATTRIBUTE_LOCALE);
        session.setAttribute(SESSION_ATTRIBUTE_LOCALE, newLocale);

        String url = request.getHeader("referer");
        response.sendRedirect(url);

    }


}
