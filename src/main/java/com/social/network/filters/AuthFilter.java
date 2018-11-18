package com.social.network.filters;

import com.social.network.constants.Role;
import com.social.network.dao.impl.UserDao;
import com.social.network.models.User;
import com.social.network.utils.ServerUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.social.network.utils.ServerUtils.stringIsNotEmpty;

public class AuthFilter implements Filter {
    private UserDao userDao;


    @Override
    public void init(FilterConfig filterConfig) {
        userDao = (UserDao) filterConfig.getServletContext().getAttribute("userDao");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                                throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestedUrl = ServerUtils.getRequestedUrl(request.getRequestURL().toString());
        if(requestedUrl.equals("register")) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        User user;
        if(session != null && session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");

            setRoleToRequest(request, user);

            filterChain.doFilter(request, response);
        } else if(stringIsNotEmpty(email) && stringIsNotEmpty(password) &&
                (user = userDao.getUserByCredentials(email, password)) != null) {
            HttpSession newSession = request.getSession();
            newSession.setAttribute("user", user);

            setRoleToRequest(request, user);

            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void setRoleToRequest(HttpServletRequest request, User user) {
        int role = user.getRole();
        Role roleModel = Role.getRoleByKey(role);
        request.setAttribute("role", roleModel.getRoleString());
    }

    @Override
    public void destroy() {

    }
}
