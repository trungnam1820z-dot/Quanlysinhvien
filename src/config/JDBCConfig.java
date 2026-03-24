package config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfig {

    public static Connection getConnection() throws SQLException, IOException {
        ConfigLoader config = new ConfigLoader("Config.properties");
        String URL = config.get("URL");
        String USER = config.get("USER");
        String PASSWORD = config.get("PASSWORD");
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}