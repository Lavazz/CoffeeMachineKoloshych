package by.trjava.kaloshych.controller.listener;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener initializes connection pool during loading application in container
 * and destroys them
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
@WebListener
public class ConnectionPoolListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ConnectionPoolListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initConnectionPool();
            logger.info("Connection pool initialized successfully");
        } catch (RuntimeException e) {
            logger.error("ConnectionPoolException during initializing", e);
            throw new ExceptionInInitializerError("Could not initialize pool!");
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyConnections();
        logger.info("Connection pool destroyed successfully");
    }
}

