package services;

import java.sql.*;
import java.util.*;
import models.Bill;
import database.DatabaseConnection;

public class BillService {
    
	public List<Bill> getBillsForUser(String userId) throws Exception {
	    List<Bill> bills = new ArrayList<>();
	    // This query needs to be modified to match the actual table structure
	    String sql = "SELECT b.* FROM bills b " +
	                 "JOIN customers c ON b.consumer_id = c.consumer_id " +
	                 "WHERE c.user_id = ? ORDER BY b.due_date";
	    
	    try (Connection conn = DatabaseConnection.connect();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Bill bill = new Bill();
	            bill.setId(rs.getLong("bill_id"));
	            bill.setConsumerId(rs.getLong("consumer_id"));
	            bill.setAmount(rs.getDouble("due_amount"));  // changed from amount to due_amount
	            bill.setDueDate(rs.getString("due_date"));
	            bill.setStatus(rs.getString("status"));
	            bills.add(bill);
	        }
	    }
	    return bills;
	}

    public Bill getBillById(long billId) throws Exception {
        String sql = "SELECT * FROM bills WHERE bill_id = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, billId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getLong("bill_id"));
                bill.setUserId(rs.getString("user_id"));
                bill.setAmount(rs.getDouble("amount"));
                bill.setDate(rs.getString("bill_date"));
                bill.setDueDate(rs.getString("due_date"));
                bill.setStatus(rs.getString("status"));
                return bill;
            }
        }
        return null;
    }
}