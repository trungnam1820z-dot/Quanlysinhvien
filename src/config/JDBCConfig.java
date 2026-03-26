package config;

import anotation.transactional.TransactionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfig {

    public static Connection getConnection() throws SQLException, IOException {
        Connection connection = TransactionManager.getConnection();
        if (connection != null) {
            return connection;
        }
        ConfigLoader config = new ConfigLoader("Config.properties");
        String url = config.get("URL");
        String user = config.get("USER");
        String password = config.get("PASSWORD");
        return DriverManager.getConnection(url, user, password);
    }
}