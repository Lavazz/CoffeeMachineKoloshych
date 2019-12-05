package by.trjava.kaloshych.dao.pool.connection;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ProxyConnection implements AutoCloseable {
    private static final Logger logger = Logger.getLogger(ProxyConnection.class);
    private ConnectionWrapper connectionWrapper;

    private ConnectionPool pool = DBConnectionPool.getInstance();


    public ProxyConnection(Connection connection) {
        this.connectionWrapper = new ConnectionWrapper(connection);
    }

    @Override
    public void close() {
        pool.putBackConnection(this);
    }


    public ConnectionWrapper getConnectionWrapper() {
        return connectionWrapper;
    }


    public void destroy() {
        try {
            this.connectionWrapper.realClose();
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }
}