package com.social.network.listeners;

import com.social.network.connective.Connective;
import com.social.network.dao.FriendsDao;
import com.social.network.dao.InitializationDao;
import com.social.network.dao.MessagesDao;
import com.social.network.dao.UserDao;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.SQLException;

import static com.social.network.connection.ConnectionPool.getConnectionPool;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class ContextListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ServletContextListener.class);
    public static final String CAN_T_INITIALIZE_DATABASE_SCHEMA_AND_DATA = "Can't initialize database schema and data";

    @Resource(name="jdbc/Prod")
    private static DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Connective connective = getConnectionPool(ds)::getConnection;
            InitializationDao initializationDao = new InitializationDao(connective);
            initializationDao.initializeStubData();

            ServletContext servletContext = servletContextEvent.getServletContext();

            UserDao userDao = new UserDao(connective);
            MessagesDao messagesDao = new MessagesDao(connective);
            FriendsDao friendsDao = new FriendsDao(connective);

            servletContext.setAttribute("userDao", userDao);
            servletContext.setAttribute("messagesDao", messagesDao);
            servletContext.setAttribute("friendsDao", friendsDao);

        } catch (SQLException | ClassNotFoundException e) {
            logger.error(CAN_T_INITIALIZE_DATABASE_SCHEMA_AND_DATA);
            throw new RuntimeException(CAN_T_INITIALIZE_DATABASE_SCHEMA_AND_DATA);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            getConnectionPool(ds).onDestroy();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Context can't be destroyed!");
        }
    }
}
