package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfig {

    private static final String URL = "jdbc:mariadb://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456a@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
    public static void closeConnection(Connection conn) {}
}