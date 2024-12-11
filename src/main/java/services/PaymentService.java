package services;

import java.sql.*;
import database.DatabaseConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentService {
    
    public boolean processPayment(String transactionId, String userId, 
                                Double amount, String cardNumber) throws Exception {
        String sql = "INSERT INTO transactions (transaction_id, user_id, amount, " +
                    "card_number, transaction_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, transactionId);
            pstmt.setString(2, userId);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, cardNumber.substring(12)); // Store last 4 digits only
            pstmt.setString(5, LocalDateTime.now().toString());
            pstmt.setString(6, "SUCCESS");
            
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public String generateReceipt(String transactionId) throws Exception {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                StringBuilder receipt = new StringBuilder();
                receipt.append("Payment Receipt\n");
                receipt.append("==============\n\n");
                receipt.append("Transaction ID: ").append(transactionId).append("\n");
                receipt.append("Amount: ₹").append(rs.getDouble("amount")).append("\n");
                receipt.append("Date: ").append(rs.getString("transaction_date")).append("\n");
                receipt.append("Status: ").append(rs.getString("status")).append("\n");
                return receipt.toString();
            }
            return "Receipt not found";
        }
    }
    
    public double getTotalAmount(String[] selectedBills) throws Exception {
        String sql = "SELECT SUM(amount) as total FROM bills WHERE bill_id IN (";
        for (int i = 0; i < selectedBills.length; i++) {
            sql += (i == 0 ? "?" : ", ?");
        }
        sql += ")";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < selectedBills.length; i++) {
                pstmt.setString(i + 1, selectedBills[i]);
            }
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
            return 0.0;
        }
    }
}