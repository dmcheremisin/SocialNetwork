package com.social.network.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.network.models.Notification;
import com.social.network.models.User;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dmitrii on 09.01.2019.
 */
@ServerEndpoint(value="/push", configurator=NotificationServerEndPointConfigurator.class)
public class NotificationServerEndPoint {
    private Map<Session, HttpSession> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        sessions.put(session, httpSession);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Notification notification = new ObjectMapper().readValue(message, Notification.class);
        sessions.forEach((key, value) -> {
            User user = (User) value.getAttribute("user");
            if (user.getId() == notification.getCompanion()) {
                HttpSession senderSession = sessions.get(session);
                User sender = (User) senderSession.getAttribute("user");
                key.getAsyncRemote().sendText(String.format("%s %s : %s", sender.getFirstName(), sender.getLastName(), notification.getMessage()));
            }
        });
    }
}
