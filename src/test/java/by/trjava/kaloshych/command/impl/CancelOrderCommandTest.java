package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.factory.CommandFactory;
import by.trjava.kaloshych.command.util.CommandName;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CancelOrderCommandTest {
    public static final String DO_PARAMETER_NAME = "idCartUser";
    @Test
    public void execute() {
    }

    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;


    @Before
    public void setUp() throws Exception {
        command = new CancelOrderCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test(expected = CommandException.class)
    public void test_no_param() throws ServletException, IOException, CommandException {
        Command command = new CancelOrderCommand();
        when(request.getParameter(DO_PARAMETER_NAME)).thenReturn("CartUser");
        when(request.getRequestDispatcher("/jsp/error.jsp")).thenReturn(requestDispatcher);

        command.execute(request, response);

        verify(request).getParameter(DO_PARAMETER_NAME);
        verify(request).getRequestDispatcher("/jsp/error.jsp");
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response);
    }

//    @Test
//    public void test_bad_param() throws ServletException, IOException {
//        when(request.getParameter(DO_PARAMETER_NAME)).thenReturn("test");
//        when(request.getRequestDispatcher(ERROR_PAGE_PATH)).thenReturn(requestDispatcher);
//
//        command.doGet(request, response);
//
//        verify(request).getParameter(DO_PARAMETER_NAME);
//        verify(request).getRequestDispatcher(ERROR_PAGE_PATH);
//        verify(requestDispatcher).forward(request, response);
//        verifyNoMoreInteractions(request, response);
//    }
//
//    @Test
//    public void test_ok() throws ServletException, IOException {
//        QuestionDoGetFactory strategyResolver = mock(QuestionDoGetFactory.class);
//        QuestionDoGetStrategy questionStrategy = mock(AllQuestionsStrategy.class);
//
//        when(request.getParameter(DO_PARAMETER_NAME)).thenReturn("all");
//        when(request.getRequestDispatcher(MAIN_PAGE_PATH)).thenReturn(requestDispatcher);
//        when(strategyResolver.getStrategy("all")).thenReturn(questionStrategy);
//        Whitebox.setInternalState(command, "strategyResolver", strategyResolver);
//
//        command.doGet(request, response);
//
//        verify(request).getParameter(DO_PARAMETER_NAME);
//        verify(request).getRequestDispatcher(MAIN_PAGE_PATH);
//        verify(requestDispatcher).forward(request, response);
//        verifyNoMoreInteractions(request, response);
//    }

}