package com.social.network.connection;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Dmitrii on 13.11.2018.
 */
public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final String CAN_T_GET_CONNECTION_FROM_POOL = "Can't get connection from the pool";
    private static final String CAN_RETURN_CONNECTION_TO_THE_POOL = "Can' return connection to the pool";

    private static ResourceBundle bundle = ResourceBundle.getBundle("connection");
    private static final String DB_DRIVER = bundle.getString("driver");
    private static final String DB_URL = bundle.getString("url");
    private static final String DB_USER_NAME = bundle.getString("user");
    private static final String DB_PASSWORD = bundle.getString("password");
    private static final int DB_MAX_CONNECTIONS = Integer.parseInt(bundle.getString("poolSize"));

    private static ConnectionPool cp;

    private Queue<Connection> freeConnections;
    private Queue<Connection> usedConnections;

    private ConnectionPool() throws ClassNotFoundException, SQLException {
        this.freeConnections = new ArrayBlockingQueue<>(DB_MAX_CONNECTIONS);
        this.usedConnections = new ArrayBlockingQueue<>(DB_MAX_CONNECTIONS);

        Class.forName(DB_DRIVER);
        while(freeConnections.size() < DB_MAX_CONNECTIONS) {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
            MyConnection myConnection = MyConnection.create(connection, this.freeConnections, usedConnections);
            freeConnections.add(myConnection);
        }

        logger.info("Connection pool is initialized");
    }

    /**
     * Custom connection pool. Singleton method.
     *
     * @param ds - unused parameter. It is necessary to propagate in order to have the same method signature with
     *           with module tomcat.connection.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ConnectionPool getConnectionPool(DataSource ds) throws SQLException, ClassNotFoundException {
        if (cp == null) {
            synchronized (ConnectionPool.class) {
                if(cp == null) {
                    cp = new ConnectionPool();
                }
            }
        }
        return cp;
    }


    public Connection getConnection() {
        try {
            Connection connection = getConnectionPool(null).freeConnections.poll();
            getConnectionPool(null).usedConnections.offer(connection);
            return connection;
        } catch (Exception e) {
            logger.error(CAN_T_GET_CONNECTION_FROM_POOL);
            throw new RuntimeException(CAN_T_GET_CONNECTION_FROM_POOL);
        }
    }

    public void returnConnection(Connection connection) {
        try {
            if (getConnectionPool(null).usedConnections.remove(connection)) {
                getConnectionPool(null).freeConnections.add(connection);
            }
        } catch (Exception e) {
            logger.error(CAN_RETURN_CONNECTION_TO_THE_POOL);
            throw new RuntimeException(CAN_RETURN_CONNECTION_TO_THE_POOL);
        }
    }

    public void closeConnection(MyConnection con, Statement stmt) {
        try {
            con.close();
        } catch (SQLException e ){
            logger.error("Can't close connection and return it to the pool");
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            logger.error("Can't close statement");
        }
    }

    public void onDestroy(){
        try {
            closeConnections(getConnectionPool(null).freeConnections);
            closeConnections(getConnectionPool(null).usedConnections);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Can't close free and used connections");
        }
        logger.info("All connections are closed");
    }

    private void closeConnections(Queue<Connection> queue) {
        Connection connection;
        while((connection = queue.poll()) != null) {
            try {
                ((MyConnection)connection).reallyClose();
            } catch (SQLException e) {
                logger.error("Can't really close connection in the queue");
            }
        }
    }
}
