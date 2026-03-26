package anotation.transactional;

import java.sql.Connection;

public class TransactionManager {

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();

    public static void begin(Connection conn) throws Exception {
        conn.setAutoCommit(false);
        CONNECTION_HOLDER.set(conn);
    }

    public static void commit() throws Exception {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null) {
            conn.commit();
        }
    }

    public static void rollback() {
        try {
            Connection conn = CONNECTION_HOLDER.get();
            if (conn != null) {
                conn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            Connection conn = CONNECTION_HOLDER.get();
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CONNECTION_HOLDER.remove();
        }
    }

    public static Connection getConnection() {
        return CONNECTION_HOLDER.get();
    }
}
