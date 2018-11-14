package com.social.network.listeners;

import com.social.network.connection.ConnectionPool;
import com.social.network.dao.impl.InitializationDao;
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
        InitializationDao initializationDao = null;
        try {
            initializationDao = new InitializationDao(ConnectionPool.getConnectionPool()::getConnection);
            initializationDao.initializeStubData();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Can't initialize database schema and data");
        }
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
