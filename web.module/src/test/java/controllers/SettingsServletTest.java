package controllers;

import com.social.network.controllers.SettingsServlet;
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

public class SettingsServletTest extends BaseDaoTest {
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

        when(request.getParameter("firstName")).thenReturn("Tyrion");
        when(request.getParameter("lastName")).thenReturn("Lannister");
        when(request.getParameter("dob")).thenReturn("11.01.1989");
        when(request.getParameter("sex")).thenReturn("2");
        when(request.getParameter("phone")).thenReturn("999-99-99");

        when(request.getRequestDispatcher("settings.jsp")).thenReturn(requestDispatcher);

        SettingsServlet settingsServlet = new SettingsServlet();

        settingsServlet.init(config);
        settingsServlet.doPost(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, times(1)).getParameter("firstName");
        verify(request, times(1)).getParameter("lastName");
        verify(request, times(1)).getParameter("dob");
        verify(request, times(1)).getParameter("sex");
        verify(request, times(1)).getParameter("phone");
        verify(request, times(1)).getRequestDispatcher("settings.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}
