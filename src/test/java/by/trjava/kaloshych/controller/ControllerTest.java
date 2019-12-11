package by.trjava.kaloshych.controller;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.impl.user.AuthorizationCommand;
import by.trjava.kaloshych.service.UserService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_COMMAND;
import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_PERMISSION;
import static by.trjava.kaloshych.command.configuration.PathToJSP.*;
import static org.mockito.Mockito.*;

public class ControllerTest {

    public static final String path="mainPage";

    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
UserService userService;

    @Before
    public void setUp() throws Exception {
        command = new AuthorizationCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        userService = mock(UserService.class);
    }
    @Test
    public void doGet() throws ServletException, IOException {
       when(request.getAttribute(PARAMETER_PERMISSION)).thenReturn(true);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(PATH_AUTHORIZATION);
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getRequestDispatcher(PATH_AUTHORIZATION)).thenReturn(dispatcher);
        verify(request, never()).getSession();

    }

    @Test
    public void doPost() throws ServletException, IOException {
        when(request.getAttribute(PARAMETER_PERMISSION)).thenReturn(true);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(PATH_SHOW_DRINKS);
        when(request.getRequestDispatcher(PATH_SHOW_DRINKS)).thenReturn(dispatcher);
        verify(request, never()).getSession();

    }
}