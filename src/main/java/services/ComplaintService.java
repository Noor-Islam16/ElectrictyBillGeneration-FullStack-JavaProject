package services;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import models.Complaint;
import database.DatabaseConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComplaintService {
   
    private long getConsumerId(String userId) throws Exception {
        String sql = "SELECT consumer_id FROM customers WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("consumer_id");
            }
            throw new Exception("Consumer not found for userId: " + userId);
        }
    }

    public long registerComplaint(Complaint complaint) throws Exception {
        // First get the consumer_id using userId
        long consumerId = getConsumerId(complaint.getUserId());
        
        String sql = "INSERT INTO complaints (complaint_type, category, landmark, " +
                    "customer_name, problem_description, consumer_id, address, mobile_number, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Open')";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, complaint.getComplaintType());
            pstmt.setString(2, complaint.getCategory());
            pstmt.setString(3, ""); // landmark
            pstmt.setString(4, complaint.getContactPerson());
            pstmt.setString(5, complaint.getDescription());
            pstmt.setLong(6, consumerId);
            pstmt.setString(7, complaint.getAddress());
            pstmt.setString(8, complaint.getContactNumber());
            
            pstmt.executeUpdate();
            
            // Get the generated complaint ID
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new Exception("Failed to get complaint ID");
        }
    }

    public Complaint getComplaintStatus(String complaintId) throws Exception {
        String sql = "SELECT * FROM complaints WHERE complaint_id = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, complaintId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(rs.getString("complaint_id"));
                complaint.setComplaintType(rs.getString("complaint_type"));
                complaint.setCategory(rs.getString("category")); 
                complaint.setDescription(rs.getString("problem_description"));
                complaint.setAddress(rs.getString("address"));
                complaint.setContactNumber(rs.getString("mobile_number"));
                complaint.setContactPerson(rs.getString("customer_name"));
                complaint.setStatus(rs.getString("status"));
                return complaint;
            }
        }
        return null;
    }
   
    public int getTotalComplaints() throws Exception {
        String sql = "SELECT COUNT(*) as total FROM complaints";
        
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        }
    }
   
    public int getComplaintsByStatus(String status) throws Exception {
        String sql = "SELECT COUNT(*) as count FROM complaints WHERE status = ? OR (status IS NULL AND ? = 'Open')";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setString(2, status);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count");
            }
            return 0;
        }
    }
   
    public List<Complaint> getAllComplaints() throws Exception {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT c.*, cu.customer_name FROM complaints c " +
                    "JOIN customers cu ON c.consumer_id = cu.consumer_id " +
                    "ORDER BY c.complaint_id DESC";
        
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(String.valueOf(rs.getInt("complaint_id")));
                complaint.setComplaintType(rs.getString("complaint_type"));
                complaint.setCategory(rs.getString("category"));
                complaint.setDescription(rs.getString("problem_description"));
                complaint.setAddress(rs.getString("address"));
                complaint.setContactNumber(rs.getString("mobile_number"));
                complaint.setContactPerson(rs.getString("customer_name"));
                complaint.setStatus(rs.getString("status"));
                complaints.add(complaint);
            }
        }
        return complaints;
    }
    
    public String getLastUpdateDate(String complaintId) throws Exception {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public boolean updateComplaintStatus(String complaintId, String newStatus) throws Exception {
        String sql = "UPDATE complaints SET status = ? WHERE complaint_id = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newStatus);
            pstmt.setString(2, complaintId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}