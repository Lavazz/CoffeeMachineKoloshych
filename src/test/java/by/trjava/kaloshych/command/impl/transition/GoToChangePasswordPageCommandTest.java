package by.trjava.kaloshych.command.impl.transition;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.trjava.kaloshych.command.configuration.PathToJSP.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GoToChangePasswordPageCommandTest {

    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;


    @Before
    public void setUp() {
        command = new GoToChangePasswordPageCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

    }

    @Test
    public void executeValid() throws CommandException {
        String result = command.execute(request, response);
        verify(request, never()).getSession();
        assertEquals(result, PATH_CHANGE_PASSWORD);
    }

    @Test
    public void executeInvalid() throws CommandException {
        String result = command.execute(request, response);


        assertNotEquals(result, PATH_AUTHORIZATION);
    }
}