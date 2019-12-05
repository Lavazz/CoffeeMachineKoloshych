package by.trjava.kaloshych.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;

import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;

@WebListener
public class ConnectionPoolListener implements ServletContextListener{

	private static final String DRIVER = "db.driver";
	private static final String URL = "db.url";
	private static final String USER = "db.user";
	private static final String PASSWORD = "db.password";
	private static final String POOL_SIZE = "pool.size";

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		final ServletContext servletContext = servletContextEvent.getServletContext();
		final String driver = servletContext.getInitParameter(DRIVER);
		final String url = servletContext.getInitParameter(URL);
		final String user = servletContext.getInitParameter(USER);
		final String password = servletContext.getInitParameter(PASSWORD);
		final String poolSize = servletContext.getInitParameter(POOL_SIZE);

		final DBConnectionPool pool = DBConnectionPool.getInstance();
		pool.init(driver, url, user, password, poolSize);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		DBConnectionPool.getInstance().destroyPool();
	}
}