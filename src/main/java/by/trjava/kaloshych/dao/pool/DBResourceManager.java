package by.trjava.kaloshych.dao.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager INSTANCE = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}

//public class  DBResourceManager {
//    private static final  DBResourceManager instance = new  DBResourceManager();
//
//    private  DBResourceManager() {
//    }
//
//    public static  DBResourceManager getInstance() {
//        return instance;
//    }
//
//    public String getValue(String key) throws DAOException {
//        String propFileName = "db.properties";
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        String result;
//        final Properties properties = new Properties();
//        try (InputStream input = classLoader.getResourceAsStream(propFileName);) {
//            properties.load(input);
//            result = properties.getProperty(key);
//        } catch (IOException e) {
//            throw new DAOException("IO error in db", e);
//        }
//        return result;
//    }
//}
