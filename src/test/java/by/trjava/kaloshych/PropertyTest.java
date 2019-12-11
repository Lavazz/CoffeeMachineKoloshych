package by.trjava.kaloshych;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertFalse;

public class PropertyTest {
    private static final String DB_CONFIG = "database.properties";

    @Test
    public void databasePropTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(DB_CONFIG);
        properties.load(is);
        assertFalse(properties.isEmpty());
    }

}
