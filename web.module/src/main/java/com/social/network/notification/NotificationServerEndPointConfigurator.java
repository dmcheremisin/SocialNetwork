package com.social.network.notification;

import javax.websocket.server.ServerEndpointConfig.Configurator;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by Dmitrii on 09.01.2019.
 */
public class NotificationServerEndPointConfigurator extends Configurator {
    private NotificationServerEndPoint chatServer = new NotificationServerEndPoint();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass)
            throws InstantiationException {
        return (T)chatServer;
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        System.out.println("modifyHandshake() Current thread " + Thread.currentThread().getName());
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
