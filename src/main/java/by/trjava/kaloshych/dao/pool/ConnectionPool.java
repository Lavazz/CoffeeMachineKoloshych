package by.trjava.kaloshych.dao.pool;

import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class represents methods for working with connection
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
public class ConnectionPool {
    /*Logger*/
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    /*Size of the connection pool*/
    private static final int POOL_SIZE = 15;
    /*Lock object for thread safe instantiating of connection pool*/
    private static Lock lock = new ReentrantLock();
    /*Instance of connection pool*/
    private static ConnectionPool instance;
    /*AtomicBoolean object for indicating if connection pool created*/
    private static AtomicBoolean isConnectionPoolCreated = new AtomicBoolean(false);
    /*Blocking queue to store connections to database*/
    private BlockingQueue<ProxyConnection> connectionQueue;

    /*Constructor of connection pool. Private to make it singleton*/
    private ConnectionPool() {
        connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
        try {
            initConnectionPool();
        } catch (SQLException e) {
            LOGGER.fatal("Connection pool was not created. Reason : " + e.getMessage());
            throw new RuntimeException(String.format("Connection pool was not created. Reason : ", e.getMessage()), e);
        }
    }

    /*Gets instance of connection pool*/
    public static ConnectionPool getInstance() {
        if (!isConnectionPoolCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isConnectionPoolCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /* Initializes connection pool. If connection pool size less then half of required number of connections
     * RuntimeException is thrown*/
    private void initConnectionPool() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        for (int i = 0; i < POOL_SIZE; i++) {
            createConnectionAndAddToPool();
        }
        if (!isAllConnectionsCreated()) {
            tryToRecreateConnections();
            if (connectionQueue.size() > POOL_SIZE / 2) {
                LOGGER.fatal("Available connections: " + connectionQueue.size());
            } else {
                throw new RuntimeException("Connection pool has not enough connections to proceed working");
            }
        } else {
            LOGGER.fatal("All connections were created successfully. Connection pool size: " + connectionQueue.size() + " connections");
        }
    }

    /*Checks if all connections were created*/
    private boolean isAllConnectionsCreated() {
        return connectionQueue.size() == POOL_SIZE;
    }

    /* Gets connection from connection pool*/
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.fatal("Can not get connection from pool. Reason : " + e.getMessage());
        }
        return connection;
    }

    /*Gets size of connection pool*/
    public int size() {
        return connectionQueue.size();
    }

    /*Returns connection to connection pool*/
    void releaseConnection(ProxyConnection connection) {
        connectionQueue.offer(connection);
    }

    /*Destroys all connections and deregisters drivers*/
    public void destroyConnections() {
        int size = connectionQueue.size();
        for (int i = 0; i < size; i++) {
            try {
                ProxyConnection connection = connectionQueue.take();
                connection.closeConnection();
            } catch (InterruptedException e) {
                LOGGER.fatal("Can not take connection from pool. Reason : " + e.getMessage());
            } catch (SQLException e) {
                LOGGER.fatal("Can not close connection. Reason :" + e.getMessage());
            }
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.fatal("Can not deregister driver. Reason :" + e.getMessage());
        }
    }

    /*Creates connection and adds to connection pool*/
    private void createConnectionAndAddToPool() {
        try {
            ProxyConnection connection = ConnectionCreator.getConnection();
            connectionQueue.put(connection);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal(e.getMessage());
        } catch (InterruptedException e) {
            LOGGER.fatal("Connection was not added to pool. Reason : " + e.getMessage());
        }
    }

    /*Tries to recreate connections, if not all were created */
    private void tryToRecreateConnections() {
        int difference = POOL_SIZE - connectionQueue.size();
        for (int i = 0; i < difference; i++) {
            createConnectionAndAddToPool();
        }
    }
}