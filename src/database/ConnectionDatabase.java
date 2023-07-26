package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    public Connection getConnection() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio", "root", "root");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
