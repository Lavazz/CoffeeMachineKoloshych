package by.trjava.coffeemachine.controller.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public final class CreatorFullURL {

    private CreatorFullURL() {
    }

    public static String create(HttpServletRequest request) {
        String url = "";

        Enumeration<String> paramNames = request.getParameterNames();

        String paramName;
        String paramValue;

        while(paramNames.hasMoreElements()) {
            paramName = paramNames.nextElement();

            paramValue = request.getParameter(paramName);
            url = url + paramName + "=" + paramValue + "&";
        }

        url = request.getRequestURL() + "?" + url;

        return url;

    }
}
