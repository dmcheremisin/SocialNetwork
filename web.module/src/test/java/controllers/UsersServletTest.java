package controllers;

import com.social.network.controllers.UsersServlet;
import com.social.network.models.User;
import dao.BaseDaoTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersServletTest extends BaseDaoTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    ServletContext context;
    @Mock
    ServletConfig config;
    @Mock
    RequestDispatcher requestDispatcher;

    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testServlet() throws Exception {
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(config.getServletContext()).thenReturn(context);

        User userFromSession = new User();
        userFromSession.setId(1);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession(false).getAttribute("user")).thenReturn(userFromSession);

        when(request.getParameter("search")).thenReturn("Lannister");
        when(request.getParameter("page")).thenReturn("1");

        when(request.getRequestDispatcher("users.jsp")).thenReturn(requestDispatcher);

        UsersServlet usersServlet = new UsersServlet();

        usersServlet.init(config);
        usersServlet.doGet(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, times(1)).getParameter("search");
        verify(request, times(1)).getParameter("page");
        verify(request, times(1)).getRequestDispatcher("users.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}
