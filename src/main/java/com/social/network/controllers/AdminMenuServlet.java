package com.social.network.controllers;

import com.social.network.constants.Role;
import com.social.network.dao.UserDao;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.social.network.constants.Role.ADMIN;
import static com.social.network.constants.Role.MEMBER;
import static com.social.network.utils.ServerUtils.getUserFromSession;
import static com.social.network.utils.ServerUtils.isInteger;
import static com.social.network.utils.ServerUtils.isNotBlank;

/**
 * Created by Dmitrii on 02.12.2018.
 */
public class AdminMenuServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProfileServlet.class);
    public static final String USER_TRIED_TO_PERFORM_ADMIN_ACTION = "User with id=%s tried to perform admin action=%s";

    private UserDao userDao;

    @Override
    public void init() {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getUserFromSession(req);
        Role role = Role.getRoleByKey(user.getRole());

        String action = req.getParameter("action");
        String userId = req.getParameter("userId");

        if(ADMIN.equals(role) && isNotBlank(action) && isInteger(userId)) {
            int userActionId = Integer.parseInt(userId);
            switch (action) {
                case "block":
                    userDao.blockUnblock(userActionId, true);
                    break;
                case "unblock":
                    userDao.blockUnblock(userActionId, false);
                    break;
                case "makeAdmin":
                    userDao.setPriviliges(userActionId, ADMIN);
                    break;
                case "makeUsual":
                    userDao.setPriviliges(userActionId, MEMBER);
                    break;
            }
        } else {
            String message = String.format(USER_TRIED_TO_PERFORM_ADMIN_ACTION, userId, action);
            logger.error(message);
            throw new RuntimeException(message);
        }
        resp.sendRedirect(req.getHeader("referer"));
    }
}
