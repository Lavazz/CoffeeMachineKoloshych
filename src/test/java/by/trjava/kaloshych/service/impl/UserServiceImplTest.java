package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.UserDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void initPool() {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initConnectionPool();
    }

    @AfterClass
    public static void ConnectionClose() {
        connectionPool.destroyConnections();
    }

    @Test
    public void authorizationValid() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.getUserByLogin("Kate");
            assertNotNull(user);
        } catch (DAOException e) {
            fail("Failed with SQL or conection");
        }
    }

    @Test(expected = java.lang.AssertionError.class)
    public void authorizationInvalid() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.getUserByLogin("Kate21");
            assertNull(user);
        } catch (DAOException e) {
            fail("Failed with SQL or conection");
        }
    }
}