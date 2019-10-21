package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.exception.NotDBDriverException;

import java.util.ResourceBundle;

public class SQLDAO {
    protected static final String driver;
    protected static final String url;
    protected static final String login;
    protected static final String password;


    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";

    private static final String DB_PROPERTIES_FILE_PATH = "db";


    static {

        ResourceBundle jdbcProperties = ResourceBundle.getBundle(DB_PROPERTIES_FILE_PATH);

        driver = jdbcProperties.getString(DB_DRIVER);
        url = jdbcProperties.getString(DB_URL);
        login = jdbcProperties.getString(DB_LOGIN);
        password = jdbcProperties.getString(DB_PASSWORD);

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // log
            throw new NotDBDriverException("Can't find driver.", e);
        }
    }

    public SQLDAO() {}
}