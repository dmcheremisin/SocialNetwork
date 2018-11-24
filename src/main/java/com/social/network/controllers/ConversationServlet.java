package com.social.network.controllers;

import com.social.network.dao.impl.MessagesDao;
import com.social.network.models.Message;
import com.social.network.models.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.social.network.utils.ServerUtils.isInteger;

public class ConversationServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ConversationServlet.class);

    private MessagesDao messagesDao;

    @Override
    public void init() throws ServletException {
        messagesDao = (MessagesDao) getServletContext().getAttribute("messagesDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        String companion = req.getParameter("companion");
        if (isInteger(companion)) {
            int companionId = Integer.parseInt(companion);
            User companionUser;
            List<Message> bothMessages = messagesDao.getBothMessages(userId, companionId);

            Message message = bothMessages.get(0);
            companionUser = message.getSender().getId() == companionId ? message.getSender() : message.getReceiver();

            req.setAttribute("conversation", bothMessages);
            req.setAttribute("companionUser", companionUser);
        }

        req.getRequestDispatcher("conversation.jsp").forward(req, resp);
    }
}
