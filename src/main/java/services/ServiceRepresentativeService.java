package services;

import java.sql.*;
import database.DatabaseConnection;

public class ServiceRepresentativeService {
    
    public boolean validateLogin(String username, String password) throws Exception {
        String sql = "SELECT * FROM service_representatives WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Debug print
            System.out.println("Executing query for username: " + username);
            System.out.println("SQL: " + sql);
            
            ResultSet rs = pstmt.executeQuery();
            boolean isValid = rs.next();
            
            // Debug print
            System.out.println("Login result: " + isValid);
            
            return isValid;
        }
    }
    
    public String getServiceRepId(String username) throws Exception {
        String sql = "SELECT rep_id FROM service_representatives WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("rep_id");
            }
            return null;
        }
    }
}