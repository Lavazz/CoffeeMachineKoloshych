package by.trjava.kaloshych.dao.pool;

import by.trjava.kaloshych.dao.pool.connection.ProxyConnection;

public interface ConnectionPool {

        ProxyConnection getConnection();

        void putBackConnection(ProxyConnection connection);

        void destroyPool();
    }
