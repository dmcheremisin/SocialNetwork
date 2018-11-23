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

public class MessagesServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MessagesServlet.class);

    private MessagesDao messagesDao;

    @Override
    public void init() throws ServletException {
        messagesDao = (MessagesDao) getServletContext().getAttribute("messagesDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Message> lastMessages = messagesDao.getLastMessages(user.getId());
        req.setAttribute("lastMessages", lastMessages);
        req.getRequestDispatcher("messages.jsp").forward(req, resp);
    }
}
