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
import static com.social.network.utils.ServerUtils.isInteger;
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = getUserFromSession(req);
        String search = req.getParameter("search");
        String page = req.getParameter("page");
        int pageNum = isInteger(page) ? Integer.parseInt(page) : 0;

        List<User> users;
        if(isNotBlank(search)) {
            logger.info("User search request: " + search);
            users = userDao.searchAll(userFromSession.getId(), pageNum, search);
        } else {
            users = userDao.getAll(userFromSession.getId(), pageNum);
        }

        req.setAttribute("users", users);
        req.setAttribute("page", pageNum);
        req.setAttribute("search", search);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

}
