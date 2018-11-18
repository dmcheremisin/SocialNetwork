package com.social.network.controllers;

import com.social.network.dao.impl.UserDao;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.social.network.utils.ServerUtils.setRoleToRequest;

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
        String passwordConfirmation = req.getParameter("password-confirm");
        if (password.equals(passwordConfirmation)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            User insertedUser = userDao.insert(user);
            HttpSession session = req.getSession();
            session.setAttribute("user", insertedUser);
            setRoleToRequest(req, insertedUser);
        } else {
            throw new RuntimeException("Passwords doesn't match");
        }
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);

    }
}
/*
Full registration
String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String dob = req.getParameter("dob");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dob);
            Integer sex = Integer.parseInt(req.getParameter("sex"));
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setDob(date);
            user.setSex(sex);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
 */