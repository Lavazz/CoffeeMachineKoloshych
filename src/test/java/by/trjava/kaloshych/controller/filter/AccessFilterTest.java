package by.trjava.kaloshych.controller.filter;

import by.trjava.kaloshych.entity.User;
import org.junit.Test;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_COMMAND;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;

public class AccessFilterTest {
    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Mock
    private User user;

    @InjectMocks
    private AccessFilter accessFilter;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterConfig filterConfig;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        Mockito.doReturn("logout userAccount doRating makeReview viewUsers addMovie banUser" +
                " goToAddMovie goToEditUser").when(filterConfig).getInitParameter("illegalForGuest");
        Mockito.doReturn("goToLogin login goToRegistration registration viewUsers addMovie banUser" +
                " goToAddMovie goToEditUser").when(filterConfig).getInitParameter("illegalForUser");
        Mockito.doReturn("goToLogin login goToRegistration registration")
                .when(filterConfig).getInitParameter("illegalForAdmin");

        accessFilter.init(filterConfig);
    }

    @Test
    public void testGuestDoFilter() throws Exception {
        Mockito.doReturn(session).when(httpServletRequest).getSession();
        Mockito.doReturn(null).when(session).getAttribute("user");
        Mockito.doReturn("logout").when(httpServletRequest).getParameter("command");
       // String command = request.getParameter(PARAMETER_COMMAND);
        accessFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(httpServletResponse).sendRedirect("main?command=goToIndexPage");
    }

//    @Test
//    public void testUserDoFilter() throws Exception {
//        Mockito.doReturn(session).when(httpServletRequest).getSession();
//        Mockito.doReturn(user).when(session).getAttribute(Attribute.ATTRIBUTE_USER);
//        Mockito.doReturn("login").when(httpServletRequest).getParameter("command");
//        Mockito.doReturn(2).when(user).getRoleId();
//
//        accessFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
//
//        verify(httpServletResponse).sendRedirect("controller?command=goToError");
//    }
//
//    @Test
//    public void testAdminDoFilter() throws Exception {
//        Mockito.doReturn(session).when(httpServletRequest).getSession();
//        Mockito.doReturn(user).when(session).getAttribute(Attribute.ATTRIBUTE_USER);
//        Mockito.doReturn("login").when(httpServletRequest).getParameter("command");
//        Mockito.doReturn(1).when(user).getRoleId();
//
//        accessFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
//
//        verify(httpServletResponse).sendRedirect("controller?command=goToError");
//    }
}