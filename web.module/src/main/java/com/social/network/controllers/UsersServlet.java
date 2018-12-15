package com.social.network.controllers;

import com.social.network.dao.UserDao;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.social.network.utils.ServerUtils.getUserFromSession;
import static com.social.network.utils.ServerUtils.isNotBlank;

/**
 * Created by Dmitrii on 28.11.2018.
 */
public class UsersServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UsersServlet.class);
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = getUserFromSession(req);
        List<User> users = userDao.getAll();
        String search = req.getParameter("search");

        Stream<User> filterSessionUser = users.stream()
                .filter(user -> user.getId() != userFromSession.getId());
        if(isNotBlank(search)){
            logger.info("User search request: " + search);
            String name = search.toLowerCase();
            users = filterSessionUser
                    .filter(u -> searchByName(name, u))
                    .collect(Collectors.toList());
        } else {
            users = filterSessionUser.collect(Collectors.toList());
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    static boolean searchByName(String name, User u) {
        return (u.getFirstName().toLowerCase() + " " + u.getLastName().toLowerCase()).contains(name) ||
                (u.getLastName().toLowerCase() + " " + u.getFirstName().toLowerCase()).contains(name);
    }
}
