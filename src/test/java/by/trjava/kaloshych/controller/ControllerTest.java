package by.trjava.kaloshych.controller;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.impl.transition.GoToMainPageCommand;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControllerTest {

    public static final String path="/WEB-INF/jsp/mainPage.jsp";

    private Controller command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;


    @Before
    public void setUp() throws Exception {
        command = new Controller();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
    }
    @Test
    public void doGetTrue() throws CommandException, ServletException, IOException {
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        command.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }







    @Test
    public void doPost() {
    }
}