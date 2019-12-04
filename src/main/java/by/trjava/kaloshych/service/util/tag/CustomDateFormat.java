package by.trjava.kaloshych.service.util.tag;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class is used to represent the date in the required format.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Date
 * @see SimpleDateFormat
 * @since JDK1.0
 */
public class CustomDateFormat {
    /**
     * Format is used to write information about date to the database and calendar in jsp-page.
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    private CustomDateFormat() {
    }

    /**
     * Returns the string representation of date in the format yyyy-MM-dd.
     *
     * @param date object {@code Date}
     * @return the string representation of date in the format yyyy-MM-dd.
     */
    public static String getDateFormat(Date date) {
        return date == null ? null : DATE_FORMAT.format(date);
    }
}
