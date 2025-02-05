/*package electricity.billing.system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    Connection connection;
    Statement statement;

    Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_system", "root", "");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/



// Database.java
package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    Connection connection;
    Statement statement;

    public Database() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_system", "root", "");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include the library in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
