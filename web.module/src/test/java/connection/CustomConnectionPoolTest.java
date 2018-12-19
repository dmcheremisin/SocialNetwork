package connection;

import com.social.network.connection.ConnectionPool;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.Assert.assertNotNull;

public class CustomConnectionPoolTest {
    private static ConnectionPool connectionPool;
    private static ResourceBundle bundle = ResourceBundle.getBundle("connection");

    @BeforeClass
    public static void init() {
        try {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(bundle.getString("url"));
            connectionPool = ConnectionPool.getConnectionPool(ds);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        connectionPool.returnConnection(connection);
    }
}
