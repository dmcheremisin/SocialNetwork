package com.social.network.connection;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static final String CAN_T_GET_CONNECTION_FROM_POOL = "Can't get tomcat connection from the pool";
    private static final String RETURN_CONNECTION_ERROR = "Return connection error";

    private static ConnectionPool cp;
    private final DataSource ds;

    private ConnectionPool(DataSource ds){
        this.ds = ds;
    }

    /**
     * Tomcat connection pool provided by DataSource ds
     *
     * @param ds - Tomcat DataSource, provides connection pool
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ConnectionPool getConnectionPool(DataSource ds) throws SQLException, ClassNotFoundException {
        if (cp == null) {
            synchronized (ConnectionPool.class) {
                if(cp == null) {
                    cp = new ConnectionPool(ds);
                }
            }
        }
        return cp;
    }

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error(CAN_T_GET_CONNECTION_FROM_POOL);
            throw new RuntimeException(e);
        }
    }

    @Ignore
    public void onDestroy(){
    }

    public void returnConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            logger.error(RETURN_CONNECTION_ERROR);
            throw new RuntimeException(e);
        }
    }
}
