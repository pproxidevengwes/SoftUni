package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    private static Connection connection;
    private static final String jdbcString = "jdbc:mysql://localhost:3306/";

    public static void createConnection(String username, String password, String dbName) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "amara2113");

        connection = DriverManager.getConnection(jdbcString + dbName, props);
    }

    public static Connection getConnection() {
        return connection;
    }
}
