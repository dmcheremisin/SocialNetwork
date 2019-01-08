package controllers;

import com.social.network.controllers.UpdatePasswordServlet;
import com.social.network.models.User;
import dao.BaseDaoTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.social.network.utils.Encryption.encryptPassword;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdatePasswordServletTest extends BaseDaoTest {
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

    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testServlet() throws Exception {
        when(context.getAttribute("userDao")).thenReturn(userDao);
        when(config.getServletContext()).thenReturn(context);

        User userFromSession = new User();
        userFromSession.setId(1);
        userFromSession.setPassword(encryptPassword("aaa123"));
        when(request.getSession(false)).thenReturn(session);
        when(request.getSession(false).getAttribute("user")).thenReturn(userFromSession);

        when(request.getParameter("oldPassword")).thenReturn("aaa123");
        when(request.getParameter("password")).thenReturn("123aaa");
        when(request.getParameter("password-confirm")).thenReturn("123aaa");

        UpdatePasswordServlet updatePasswordServlet = new UpdatePasswordServlet();

        updatePasswordServlet.init(config);
        updatePasswordServlet.doPost(request, response);

        verify(request, atLeastOnce()).getSession(false);
        verify(request, times(1)).getParameter("oldPassword");
        verify(request, times(1)).getParameter("password");
        verify(request, times(1)).getParameter("password-confirm");
        verify(response, times(1)).sendRedirect("/profile");
    }
}
