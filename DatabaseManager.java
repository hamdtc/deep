import java.io.*;
import java.net.*;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/snoopdeepweb";
        String username = "yourusername";
        String password = "yourpassword";
        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE crawl_results (id INT AUTO_INCREMENT PRIMARY KEY, seed_url VARCHAR(255), current_url VARCHAR(255), html TEXT, base64_image TEXT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void insertData(String seedUrl, String currentUrl, String html, String base64Image) throws SQLException {
        String sql = "INSERT INTO crawl_results (seed_url, current_url, html, base64_image) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, seedUrl);
            statement.setString(2, currentUrl);
            statement.setString(3, html);
            statement.setString(4, base64Image);
            statement.executeUpdate();
        }
    }
}
