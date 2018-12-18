package connection;

import com.social.network.connection.ConnectionPool;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomConnectionPoolTest {
    private static ConnectionPool connectionPool;
    private static Set<Connection> connections;

    private static ResourceBundle bundle = ResourceBundle.getBundle("connection");

    @BeforeClass
    public static void init() {
        try {
            connectionPool = ConnectionPool.getConnectionPool(null);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        connections = new HashSet<>();
    }

    @Test
    public void testConnection() {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        connectionPool.returnConnection(connection);
    }
}
