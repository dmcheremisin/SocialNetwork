package com.social.network.controllers;

import com.social.network.dao.UserDao;
import com.social.network.models.User;
import com.social.network.utils.ServerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.social.network.utils.Encryption.encryptPassword;
import static com.social.network.utils.ServerUtils.getUserFromSession;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public class UpdatePasswordServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UpdatePasswordServlet.class);
    public static final String PASSWORDS_DOESN_T_MATCH = "Passwords do not match";

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = getUserFromSession(req);
        String oldPassword = user.getPassword();

        String oldPasswordForm = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("password-confirm");
        if (password.equals(passwordConfirmation) &&
                ServerUtils.isNotBlank(oldPasswordForm) && oldPassword.equals(encryptPassword(oldPasswordForm))) {
            password = encryptPassword(password);
            user.setPassword(password);

            User updatedUser = userDao.updatePassword(user);
            session.setAttribute("user", updatedUser);
        } else {
            logger.error(PASSWORDS_DOESN_T_MATCH);
            throw new RuntimeException(PASSWORDS_DOESN_T_MATCH);
        }
        resp.sendRedirect("/profile");
    }
}