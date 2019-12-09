package by.trjava.kaloshych.dao.impl.util;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class JDBCShutter {
    protected static final ConnectionPool pool = DBConnectionPool.getInstance();
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
