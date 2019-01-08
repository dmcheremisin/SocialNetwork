package com.social.network.controllers;

import com.social.network.dao.UserDao;
import com.social.network.models.User;
import com.social.network.utils.ServerUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.social.network.utils.ServerUtils.getUserFromSession;

/**
 * Created by Dmitrii on 19.11.2018.
 */
public class SettingsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SettingsServlet.class);
    private static final String CAN_T_PARSE_USER_DATA_PARAMETERS = "Can't parse user data parameters";

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("settings.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        try {
            String firstName = req.getParameter("firstName");
            if (ServerUtils.isNotBlank(firstName)){
                user.setFirstName(firstName);
            }

            String lastName = req.getParameter("lastName");
            if (ServerUtils.isNotBlank(lastName)){
                user.setLastName(lastName);
            }

            String dob = req.getParameter("dob");
            if (ServerUtils.isNotBlank(dob)){
                user.setDob(dob);
            }

            String sex = req.getParameter("sex");
            if (ServerUtils.isNotBlank(sex) && ServerUtils.isInteger(sex)){
                user.setSex(Integer.parseInt(sex));
            }

            String phone = req.getParameter("phone");
            if (ServerUtils.isNotBlank(phone)){
                user.setPhone(phone);
            }

            userDao.update(user);

            doGet(req, resp);
        } catch (Exception e) {
            logger.error(CAN_T_PARSE_USER_DATA_PARAMETERS);
            throw new RuntimeException(e);
        }
    }
}
