package controllers;

import com.social.network.controllers.RegistrationServlet;
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

public class RegistrationServletTest extends BaseDaoTest {
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
    public void positiveTest() throws Exception {
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(config.getServletContext()).thenReturn(context);

        User userFromSession = new User();
        userFromSession.setId(1);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(false).getAttribute("user")).thenReturn(userFromSession);

        when(request.getParameter("register_email")).thenReturn("aaa@aaa.uk.co");
        when(request.getParameter("register_password")).thenReturn("b1bbbb");
        when(request.getParameter("register_password_confirm")).thenReturn("b1bbbb");

        RegistrationServlet registrationServlet = new RegistrationServlet();

        registrationServlet.init(config);
        registrationServlet.doPost(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, atLeastOnce()).getSession();
        verify(request, times(1)).getParameter("register_email");
        verify(request, times(1)).getParameter("register_password");
        verify(request, times(1)).getParameter("register_password_confirm");
        verify(response, times(1)).sendRedirect("/profile");
    }

    @Test
    public void negativeTest() throws Exception {
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(config.getServletContext()).thenReturn(context);

        User userFromSession = new User();
        userFromSession.setId(1);
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession()).thenReturn(session);
        when(request.getSession(false).getAttribute("user")).thenReturn(userFromSession);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        when(request.getParameter("register_email")).thenReturn("afdsfasfsdfdasf");

        RegistrationServlet registrationServlet = new RegistrationServlet();

        registrationServlet.init(config);
        registrationServlet.doPost(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, times(0)).getSession();

        verify(request, times(1)).getParameter("register_email");
        verify(request, times(1)).getParameter("register_password");
        verify(request, times(1)).getParameter("register_password_confirm");

        verify(request, times(1)).getRequestDispatcher("index.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}
