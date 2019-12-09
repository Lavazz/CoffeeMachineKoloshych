package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.factory.CommandFactory;
import by.trjava.kaloshych.command.factory.impl.CommandFactoryImpl;
import by.trjava.kaloshych.controller.Controller;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.entity.UserStatus;
import by.trjava.kaloshych.service.UserService;
import by.trjava.kaloshych.service.exception.ServiceException;
import by.trjava.kaloshych.service.exception.WrongAuthorizationException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_AUTHORIZATION;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_MAIN_PAGE;
import static by.trjava.kaloshych.command.util.CommandName.AUTHORIZATION;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorizationCommandTest {


    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    UserService userService;

    @Before
    public void setUp() throws Exception {
        CommandFactory commandFactory = CommandFactoryImpl.getInstance();
        command = commandFactory.createCommand(AUTHORIZATION);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        userService = mock(UserService.class);
    }

    @Test(expected = CommandException.class)
    public void execute() throws ServiceException, CommandException {
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        doThrow(ServiceException.class).when(userService).authorization(anyString(), anyString());
        command.execute(request, response);
    }

    @Test
    public void execute_invalidUserInformationExceptionFromSignIn()
            throws ServiceException, CommandException, ServletException, IOException {

        User user = mock(User.class);
        UserStatus userStatus = UserStatus.CUSTOMER;
        HttpSession session = mock(HttpSession.class);
        //when
        when(request.getParameter("login")).thenReturn("Username");
        when(request.getParameter("password")).thenReturn("Password");
        when(request.getSession()).thenReturn(session);
        doReturn(user).when(userService).authorization(anyString(), anyString());
        when(user.getUserStatus()).thenReturn(userStatus);
        doThrow(WrongAuthorizationException.class).when(userService).authorization(anyString(), anyString());
        String result = command.execute(request, response);
        //then
        assertEquals(result, PATH_AUTHORIZATION);
    }

    @Test
    public void execute_validParameters()
            throws ServiceException, CommandException, ServletException, IOException {
        //given
        User user = mock(User.class);
        UserStatus userStatus = UserStatus.ADMIN;
        HttpSession session = mock(HttpSession.class);

        //when
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("1111");
        when(request.getSession()).thenReturn(session);
        when(userService.authorization(anyString(), anyString())).thenReturn(user);
        when(user.getUserStatus()).thenReturn(userStatus);

        String result = command.execute(request,response);
        //then
        assertEquals(result, PATH_MAIN_PAGE);
    }
}
