package by.trjava.kaloshych.service.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    public static final String ruLocale = "ru";
    public static final String enLocale = "en";

    private ResourceBundle ruBundle = ResourceBundle.getBundle("locale", new Locale(ruLocale, "RU"));
    private ResourceBundle enBundle = ResourceBundle.getBundle("locale", new Locale(enLocale, "EN"));

    private final static MessageManager INSTANCE = new MessageManager();

    public static MessageManager getInstance() {
        return INSTANCE;
    }

    public String getMessage(String key, String locale) {
        if (enLocale.equals(locale)) {
            return enBundle.getString(key);
        }
        return ruBundle.getString(key);
    }
}
