package com.social.network.controllers;

import com.social.network.dao.impl.UserDao;
import com.social.network.models.User;
import com.social.network.utils.ServerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dmitrii on 19.11.2018.
 */
public class ProfileServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegistrationServlet.class);

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        try {
            String firstName = req.getParameter("firstName");
            if (ServerUtils.stringIsNotEmpty(firstName)){
                user.setFirstName(req.getParameter("firstName"));
            }

            String lastName = req.getParameter("lastName");
            if (ServerUtils.stringIsNotEmpty(lastName)){
                user.setLastName(lastName);
            }

            String dob = req.getParameter("dob");
            if (ServerUtils.stringIsNotEmpty(dob)){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(dob);
                user.setDob(date);
            }

            String sex = req.getParameter("sex");
            if (ServerUtils.stringIsNotEmpty(sex) && ServerUtils.isInteger(sex)){
                user.setSex(Integer.parseInt(sex));
            }

            String phone = req.getParameter("phone");
            if (ServerUtils.stringIsNotEmpty(phone)){
                user.setPhone(phone);
            }

            userDao.update(user);

            doGet(req, resp);
        } catch (ParseException e) {
            logger.error("Can't parse user data parameters");
            throw new RuntimeException();
        }
    }
}
