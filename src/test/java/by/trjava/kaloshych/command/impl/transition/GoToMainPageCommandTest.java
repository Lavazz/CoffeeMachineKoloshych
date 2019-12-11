package by.trjava.kaloshych.command.impl.transition;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_AUTHORIZATION;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_MAIN_PAGE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GoToMainPageCommandTest {

    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;


    @Before
    public void setUp() {
        command = new GoToMainPageCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

    }

    @Test
    public void executeValid() throws CommandException {
        String result = command.execute(request, response);
        assertEquals(result, PATH_MAIN_PAGE);
        verify(request, never()).getSession();
    }

    @Test
    public void executeInvalid() throws CommandException {
        String result = command.execute(request, response);
        assertNotEquals(result, PATH_AUTHORIZATION);
    }
}