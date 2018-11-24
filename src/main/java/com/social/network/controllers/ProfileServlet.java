package com.social.network.controllers;

import com.social.network.dao.impl.MessagesDao;
import com.social.network.dao.impl.UserDao;
import com.social.network.models.Message;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static com.social.network.controllers.MessagesServlet.setCompanionToMessage;
import static com.social.network.utils.ServerUtils.getUserFromSession;
import static com.social.network.utils.ServerUtils.isInteger;

public class ProfileServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ProfileServlet.class);

    private UserDao userDao;
    private MessagesDao messagesDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        messagesDao = (MessagesDao) getServletContext().getAttribute("messagesDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId;
        String id = req.getParameter("id");
        if(isInteger(id)) {
            userId = Integer.parseInt(id);
        } else {
            User user = getUserFromSession(req);
            userId = user.getId();
        }
        User user = userDao.get(userId);
        req.setAttribute("profileUser", user);

        List<Message> recentMessages = messagesDao.getRecentMessages(userId);
        Message recent = recentMessages.stream()
                .sorted(Comparator.comparing(Message::getDate))
                .reduce((one, two) -> two)
                .orElse(null);
        if(recent != null) {
            setCompanionToMessage(userId, recent);
            req.setAttribute("message", recent);
        }
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
