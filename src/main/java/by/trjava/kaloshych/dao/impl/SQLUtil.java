package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLUtil {
    protected  final ConnectionPool pool = DBConnectionPool.getInstance();

    protected static final Logger logger = LogManager.getLogger(SQLUtil.class);


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
