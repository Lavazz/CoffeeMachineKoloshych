package by.trjava.coffeemachine.dao.impl.pool;

import by.trjava.coffeemachine.dao.exception.DAOException;
//import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class ConnectionPool {
    //   Logger logger=Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool instance = new ConnectionPool();

    private final List<Connection> connectionPool = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();
    private static int poolSize;
    private static String driverName;
    private static String url;
    private static String password;
    private static String user;
    private static final int DEFAULT_POOL_SIZE = 5;
    // private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);


    public void init() throws DAOException {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        driverName = dbResourceManager.getValue(DBParameter.DATABASE_DRIVER);
        url = dbResourceManager.getValue(DBParameter.DATABASE_URL);
        password = dbResourceManager.getValue(DBParameter.DATABASE_PASSWORD);
        user = dbResourceManager.getValue(DBParameter.DATABASE_LOGIN);
        poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DATABASE_POOLSIZE));

        poolSize = DEFAULT_POOL_SIZE;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver");

        }

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }

        System.out.println(connectionPool.size());
    }

    //
//    private static Connection createConnection(String )
//    static {
//        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
//
//        try {
//            driverName = dbResourceManager.getValue(DBParameter.DATABASE_DRIVER);
//            url = dbResourceManager.getValue(DBParameter.DATABASE_URL);
//            password = dbResourceManager.getValue(DBParameter.DATABASE_PASSWORD);
//            user = dbResourceManager.getValue(DBParameter.DATABASE_LOGIN);
//            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DATABASE_POOLSIZE));
//        } catch (NumberFormatException | DAOException e) {
//            //           LOGGER.error("Can't parse pool size. It will be 5 by default.");
//            poolSize = DEFAULT_POOL_SIZE;
//            try {
//                //               LOGGER.error("Can't parse pool size. It will be 5 by default.");
//                throw new ConnectionPoolException("Can't parse pool size. It will be 5 by default.", e);
//            } catch (ConnectionPoolException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver");
//            //           LOGGER.error("Can't find driver.");
////            try {
////                throw new ConnectionPoolException("Can't find driver.", e);
////            } catch (ConnectionPoolException ex) {
////                ex.printStackTrace();
////            }
//        }
//    }
//
//    protected ConnectionPool() {
//        System.out.println("here");
//        for (int i = 0; i < poolSize; i++) {
//            connectionPool.add(createConnection());
//        }
//    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            connectionPool.add(createConnection());
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    private static Connection createConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            //          LOGGER.error("SQLException in connection pool", e);
        }
        return con;
    }
}

