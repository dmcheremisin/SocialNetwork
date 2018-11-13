package com.social.network.listeners;

import com.social.network.dao.InitializationDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

import static com.social.network.connection.ConnectionPool.getConnectionPool;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class ContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Connection connection = getConnectionPool().getConnection();
            InitializationDao initializationDao = new InitializationDao();
            initializationDao.initializeStubData();
        } catch (Exception e) {
            throw new RuntimeException("Can't establish a connection");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            getConnectionPool().onDestroy();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
