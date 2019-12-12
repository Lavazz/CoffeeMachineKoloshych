package by.trjava.kaloshych.dao.util;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents methods for close ResultSet  and PreparedStatement
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
public class JDBCShutter {
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(JDBCShutter.class);

    public static void shut(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close rs" + e);
        }

    }

    public static void shut(ResultSet rs, PreparedStatement st) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close rs" + e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close st" + e);
        }

    }

}
