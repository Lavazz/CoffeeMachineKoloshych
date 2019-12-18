package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.entity.Drink;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrinkServiceImplTest {

    private static final String DRINK_CAPPUCCINO = "Капучино";
    private static final String DRINK_LATTE_MACCHIATO = "Латте макиато";
    private static final int DRINK_INDEX = 0;
    private static ConnectionPool connectionPool;
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DrinkDAO drinkDAO = daoFactory.getDrinkDAO();

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
    public void getAllDrinksValid() {
        try {
            Drink drink = drinkDAO.getAllDrinks().get(DRINK_INDEX);
            assertNotNull(drink);
        } catch (DAOException e) {
            fail("Failed with SQL or conection");
        }
    }

    @Test
    public void getAllDrinksValidName() {
        try {
            String drinkName = drinkDAO.getAllDrinks().get(DRINK_INDEX).getNameComponent();
            assertEquals(DRINK_LATTE_MACCHIATO, drinkName);
        } catch (DAOException e) {
            fail("Failed with SQL or conection");
        }
    }

    @Test
    public void getAllDrinksInvalidName() {
        try {
            String drinkName = drinkDAO.getAllDrinks().get(DRINK_INDEX).getNameComponent();
            assertNotEquals(DRINK_CAPPUCCINO, drinkName);
        } catch (DAOException e) {
            fail("Failed with SQL or conection");
        }
    }
}