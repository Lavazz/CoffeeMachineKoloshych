package by.trjava.kaloshych.dao.impl;

import by.trjava.kaloshych.dao.pool.ConnectionPool;
import by.trjava.kaloshych.dao.pool.exception.ConnectionPoolException;
import by.trjava.kaloshych.dao.pool.impl.DBConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLUtil {
    protected static final ConnectionPool pool = DBConnectionPool.getInstance();

    protected static final Logger logger = LogManager.getLogger(SQLUtil.class);

    public static void shut(ResultSet rs, PreparedStatement st1, PreparedStatement st2, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close rs" + e);
        }
        try {
            if (st1 != null) {
                st1.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close st2" + e);
        }
        try {
            if (st2 != null) {
                st2.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close st1" + e);
        }

        try {
            pool.releaseConnection(con);
        } catch (ConnectionPoolException e) {
            logger.warn("can't close connection pool" + e);
        }
    }

    public static void shut(ResultSet rs, PreparedStatement st, Connection con) {
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

        try {
            pool.releaseConnection(con);
        } catch (ConnectionPoolException e) {
            logger.warn("can't close connection pool" + e);
        }
    }

    public static void shut(PreparedStatement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close st" + e);
        }

        try {
            pool.releaseConnection(con);
        } catch (ConnectionPoolException e) {
            logger.warn("can't close connection pool" + e);
        }
    }

    public static void shut(ResultSet rs, PreparedStatement st1, PreparedStatement st2, PreparedStatement st3,
                             Connection con) {

        shut(rs, st1, st2, con);

        try {
            if (st3 != null) {
                st3.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close st3" + e);
        }

        try {
            pool.releaseConnection(con);
        } catch (ConnectionPoolException e) {
            logger.warn("can't close connection pool" + e);
        }
    }

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
