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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.social.network.utils.ServerUtils.getUserFromSession;

public class MessagesServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MessagesServlet.class);

    private MessagesDao messagesDao;

    @Override
    public void init() throws ServletException {
        messagesDao = (MessagesDao) getServletContext().getAttribute("messagesDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        int userId = user.getId();

        List<Message> lastMessages = messagesDao.getLastMessages(userId);
        lastMessages = lastMessages.stream()
                .sorted(Comparator.comparing(Message::getDate))
                .collect(Collectors.toList());

        Map<Integer, Message> filteredMessages = new HashMap<>();
        lastMessages.forEach(m -> {
                    int companion;
                    if (m.getSender().getId() == userId) {
                        companion = m.getReceiver().getId();
                    } else {
                        companion = m.getSender().getId();
                    }
                    m.setCompanion(companion);
                    filteredMessages.put(companion, m);
                }
        );

        req.setAttribute("lastMessages", filteredMessages.values());
        req.getRequestDispatcher("messages.jsp").forward(req, resp);
    }
}
