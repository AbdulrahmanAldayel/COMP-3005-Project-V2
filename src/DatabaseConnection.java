package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final String url = "jdbc:postgresql://localhost:5432/ProjectV2";
    private final String user = "postgres";
    private final String password = "postgres";

    /**
     * Establishes a connection to the database.
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            // Attempts to establish a connection to the given database URL
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}

