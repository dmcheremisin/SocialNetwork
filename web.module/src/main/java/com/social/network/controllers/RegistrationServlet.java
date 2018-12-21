package com.social.network.controllers;

import com.social.network.dao.UserDao;
import com.social.network.models.User;
import com.social.network.utils.Encryption;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.social.network.utils.ServerUtils.isBlank;
import static com.social.network.utils.ServerUtils.setRoleToRequest;
import static com.social.network.utils.Validation.validateEmail;
import static com.social.network.utils.Validation.validatePassword;

/**
 * Created by Dmitrii on 14.11.2018.
 */
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("password_confirm");
        addAttributesToRequest(req, email, password, passwordConfirmation);
        if(!validateEmail(email)) {
            showValidationMessage("email", req, resp);
            return;
        }
        if (!validatePassword(password)) {
            showValidationMessage("password", req, resp);
            return;
        }
        if (isBlank(passwordConfirmation) || !password.equals(password)) {
            showValidationMessage("password_confirm", req, resp);
            return;
        }

        User user = new User();
        user.setEmail(email);
        password = Encryption.encryptPassword(password);
        user.setPassword(password);

        User insertedUser = userDao.insert(user);
        HttpSession session = req.getSession();
        session.setAttribute("user", insertedUser);
        setRoleToRequest(req, insertedUser);

        resp.sendRedirect("/profile");
    }

    private void addAttributesToRequest(HttpServletRequest req, String email, String password, String passwordConfirmation) {
        req.setAttribute("emailValue", email);
        req.setAttribute("passwordValue", password);
        req.setAttribute("passwordConfirmValue", passwordConfirmation);
    }

    private void showValidationMessage(String type, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", type);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}