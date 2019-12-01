package by.trjava.kaloshych.dao.pool.impl;

import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.DBParameter;
import by.trjava.kaloshych.dao.pool.DBResourceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DBConnectionPool implements ConnectionPool {
    private static final int POOL_SIZE = 20;

    private static final DBConnectionPool instance = new DBConnectionPool();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> usingQueue;

    private String driverName;
    private String url;
    private String login;
    private String password;

    private DBConnectionPool() {
        DBResourceManager resourceManager = DBResourceManager.getInstance();
        driverName = resourceManager.getValue(DBParameter.DATABASE_DRIVER);
        url = resourceManager.getValue(DBParameter.DATABASE_URL);
        login = resourceManager.getValue(DBParameter.DATABASE_LOGIN);
        password = resourceManager.getValue(DBParameter.DATABASE_PASSWORD);
    }

    public static DBConnectionPool getInstance() {
        return instance;
    }

    public void init() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            usingQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, login, password);
                connectionQueue.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Database driver is not founded", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Access error in database", e);
        }
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        if (connectionQueue.size() != 0) {
            try {
                connection = connectionQueue.take();
                usingQueue.add(connection);
            } catch (InterruptedException e) {
                throw new ConnectionPoolException(e);
            }
        }
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (usingQueue.contains(connection)) {
            usingQueue.remove(connection);
            connectionQueue.add(connection);
        } else {
            throw new ConnectionPoolException("Cannot put connection which was created outside the pool");
        }
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        for (Connection connection : connectionQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Cannot close connection");
            }
        }
    }
}
