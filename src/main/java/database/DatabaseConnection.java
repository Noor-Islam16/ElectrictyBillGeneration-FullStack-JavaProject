package database;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:C:/Users/mdnoo/electricity_billing.db";

    public static Connection connect() throws Exception {
    	Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);
    }

    public static void createCustomersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS customers ("
                + "consumer_id BIGINT PRIMARY KEY,"
                + "bill_number INTEGER,"
                + "title VARCHAR(10),"
                + "customer_name VARCHAR(50) NOT NULL,"
                + "email VARCHAR(100) UNIQUE NOT NULL,"
                + "mobile_number VARCHAR(15) NOT NULL,"
                + "user_id VARCHAR(20) NOT NULL CHECK (LENGTH(user_id) >= 5 AND LENGTH(user_id) <= 20),"
                + "password VARCHAR(30) NOT NULL,"
                + "status VARCHAR(10) DEFAULT 'Active' CHECK (status IN ('Active', 'Inactive'))"
                + ")";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Customers table created");
        }
        catch(Exception e) {
        	
        }
    }

    public static void main(String[] args) {
        try {
            createCustomersTable();
            System.out.println("Connected and table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}