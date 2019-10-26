package by.trjava.kaloshych.dao.impl.pool;

import by.trjava.kaloshych.dao.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class  DBResourceManager {
    private static final  DBResourceManager instance = new  DBResourceManager();

    private  DBResourceManager() {
    }

    public static  DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) throws DAOException {
        String propFileName = "db.properties";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String result;
        final Properties properties = new Properties();
        try (InputStream input = classLoader.getResourceAsStream(propFileName);) {
            properties.load(input);
            result = properties.getProperty(key);
        } catch (IOException e) {
            throw new DAOException("IO error in db", e);
        }
        return result;
    }
}
