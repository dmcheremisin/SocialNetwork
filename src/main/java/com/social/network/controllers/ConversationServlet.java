package com.social.network.controllers;

import com.social.network.dao.MessagesDao;
import com.social.network.dao.UserDao;
import com.social.network.models.Message;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.social.network.utils.ServerUtils.*;

public class ConversationServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ConversationServlet.class);

    private UserDao userDao;
    private MessagesDao messagesDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        messagesDao = (MessagesDao) getServletContext().getAttribute("messagesDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        int userId = user.getId();

        String companion = req.getParameter("companion");
        if (isInteger(companion)) {
            int companionId = Integer.parseInt(companion);
            User companionUser = userDao.get(companionId);
            List<Message> bothMessages = messagesDao.getBothMessages(userId, companionId);

            req.setAttribute("conversation", bothMessages);
            req.setAttribute("companionUser", companionUser);
        }

        req.getRequestDispatcher("conversation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);

        String companion = req.getParameter("companion");
        String message = req.getParameter("message");

        if (isInteger(companion) && isNotBlank(message)) {
            int companionId = Integer.parseInt(companion);
            messagesDao.addMessage(user.getId(), companionId, message);
        }

        doGet(req, resp);
    }
}
