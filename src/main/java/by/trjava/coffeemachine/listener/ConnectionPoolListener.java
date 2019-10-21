package by.trjava.coffeemachine.listener;//package by.trjava.by.trjava.coffeemachine.listener;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//import by.trjava.by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//
//public class ConnectionPoolListener implements ServletContextListener{
//
//	private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);
//
//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//
//		ConnectionPool.getInstance().releaseConnection();
//
//		logger.info("Connection pool destroyed successfully");
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//
//		try {
//			ConnectionPool.getInstance().initPoolData();
//			logger.info("Connection pool initialized successfully");
//		} catch (ConnectionPoolException e) {
//			logger.error("ConnectionPoolException during initializing", e);
//			throw new ExceptionInInitializerError("Could not initialize pool!");
//		}
//	}
//
//
//}
