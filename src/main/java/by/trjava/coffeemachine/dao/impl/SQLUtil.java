package by.trjava.coffeemachine.dao.impl;

import by.trjava.coffeemachine.dao.impl.pool.ConnectionPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLUtil {
    protected static final ConnectionPool pool = ConnectionPool.getInstance();

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

        pool.releaseConnection(con);
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

        pool.releaseConnection(con);
    }

    public static void shut(PreparedStatement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            logger.warn("can't close st" + e);
        }

        pool.releaseConnection(con);
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

        pool.releaseConnection(con);
    }
}
