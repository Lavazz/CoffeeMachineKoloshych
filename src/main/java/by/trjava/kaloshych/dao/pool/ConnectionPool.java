package by.trjava.kaloshych.dao.pool;


import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {
    void init() throws ConnectionPoolException;

    Connection getConnection() throws ConnectionPoolException;

    void releaseConnection(Connection connection) throws ConnectionPoolException;

    void destroyPool() throws ConnectionPoolException;
}