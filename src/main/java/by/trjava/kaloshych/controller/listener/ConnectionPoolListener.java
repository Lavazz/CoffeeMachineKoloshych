package by.trjava.kaloshych.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.trjava.kaloshych.dao.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.impl.pool.ConnectionPool;
import org.apache.log4j.Logger;

@WebListener
public class ConnectionPoolListener implements ServletContextListener{

	private static final Logger logger = Logger.getLogger(ConnectionPoolListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	//	ConnectionPool.getInstance().releaseConnection();

		logger.info("Connection pool destroyed successfully");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {
			   ConnectionPool.getInstance().init();
			logger.info("Connection pool initialized successfully");
		} catch (DAOException e) {
            logger.error("ConnectionPoolException during initializing", e);
            throw new ExceptionInInitializerError("Could not initialize pool!");
        }
    }


}
