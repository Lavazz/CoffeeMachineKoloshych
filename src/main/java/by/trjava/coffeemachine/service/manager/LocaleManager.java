package by.trjava.coffeemachine.service.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    public static final String ruLocal = "ru";
    public static final String enLocal = "en";

    private ResourceBundle ruBundle = ResourceBundle.getBundle("locale", new Locale(ruLocal, "RU"));
    private ResourceBundle enBundle = ResourceBundle.getBundle("locale", new Locale(enLocal, "EN"));

    private final static LocaleManager INSTANCE = new LocaleManager();

    public static LocaleManager getInstance() {
        return INSTANCE;
    }

    public String getMessage(String key, String local) {
        if (enLocal.equals(local)) {
            return enBundle.getString(key);
        }
        return ruBundle.getString(key);
    }
}
