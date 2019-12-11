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
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyConnections();
        logger.info("Connection pool destroyed successfully");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance().getConnection();
        logger.info("Connection pool initialized successfully");

    }
}