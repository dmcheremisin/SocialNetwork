package com.social.network.listeners;

import com.social.network.dao.InitializationDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

import static com.social.network.connection.ConnectionPool.getConnectionPool;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class ContextListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InitializationDao initializationDao = new InitializationDao();
        initializationDao.initializeStubData();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            getConnectionPool().onDestroy();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Context can't be destroyed!");
        }
    }
}
