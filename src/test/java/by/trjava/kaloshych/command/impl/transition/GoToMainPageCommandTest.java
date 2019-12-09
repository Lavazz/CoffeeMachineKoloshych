package by.trjava.kaloshych.command.impl.transition;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.impl.CancelOrderCommand;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_PERMISSION;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GoToMainPageCommandTest {

    public static final String path="/WEB-INF/jsp/mainPage.jsp";

    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;


    @Before
    public void setUp() throws Exception {
        command = new GoToMainPageCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
       dispatcher = mock(RequestDispatcher.class);
        final boolean permission = true;
    }
    @Test
    public void executeTrue() throws CommandException, ServletException, IOException {
       when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
       command.execute(request, response);

       verify(request, times(1)).getRequestDispatcher(path);
       verify(request, never()).getSession();
       verify(dispatcher).forward(request, response);
    }
}