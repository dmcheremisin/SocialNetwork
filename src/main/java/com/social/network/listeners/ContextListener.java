package com.social.network.listeners;

import com.social.network.connection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class ContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Connection connection = ConnectionPool.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Can't establish a connection");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.onDestroy();
    }
}
