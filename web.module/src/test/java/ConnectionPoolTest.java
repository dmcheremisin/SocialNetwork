import com.social.network.connection.ConnectionPool;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConnectionPoolTest {
    private ConnectionPool connectionPool;
    private Set<Connection> connections;

    private static ResourceBundle bundle = ResourceBundle.getBundle("connection");

    @Before
    public void init() {
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

    @Test
    public void testConnectionPoolSize(){
        int poolSize = Integer.parseInt(bundle.getString("poolSize"));
        for(int i=0; i < 50; i++) {
            try {
                connections.add(connectionPool.getConnection());
            } catch (Exception ignored) {}
        }
        assertEquals(poolSize, connections.size());

        connections.forEach(c -> connectionPool.returnConnection(c));
    }
}
