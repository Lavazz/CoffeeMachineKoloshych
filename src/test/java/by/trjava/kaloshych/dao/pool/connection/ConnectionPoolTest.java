package by.trjava.kaloshych.dao.pool.connection;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class ConnectionPoolTest {
    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void initPool() {
        connectionPool = ConnectionPool.getInstance();
      int  numberOfConnections = connectionPool.size();
    }

    @Test
    public void connectionPoolInitialized() {
        assertNotNull(connectionPool);
    }

}