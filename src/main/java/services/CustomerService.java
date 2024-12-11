package services;

import java.sql.*;
import database.DatabaseConnection;

public class CustomerService {
    
    public boolean validateLogin(String userId, String password) throws Exception {
        String sql = "SELECT * FROM customers WHERE user_id = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }
    
    public String getCustomerName(String userId) throws Exception {
        String sql = "SELECT customer_name FROM customers WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("customer_name");
            }
            return "User";
        }
    }
}