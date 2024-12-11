package services;
import java.sql.*;
import java.util.Scanner;
import database.DatabaseConnection;

public class ComplaintRegistration {
    private Scanner scanner = new Scanner(System.in);

    public void createComplaintTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS complaints ("
            + "complaint_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "complaint_type VARCHAR(50),"
            + "category VARCHAR(50),"
            + "landmark VARCHAR(100),"
            + "customer_name VARCHAR(50),"
            + "problem_description TEXT,"
            + "consumer_id BIGINT,"
            + "address TEXT,"
            + "mobile_number VARCHAR(15),"
            + "FOREIGN KEY (consumer_id) REFERENCES customers(consumer_id))";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Complaints table created");
        }
    }

    public void registerComplaint() throws Exception {
        try {
            System.out.println("\nEnter Complaint Details");
            
            System.out.print("Enter Complaint Type: ");
            String type = scanner.nextLine();
            
            System.out.print("Enter Category: ");
            String category = scanner.nextLine();
            
            System.out.print("Enter Landmark: ");
            String landmark = scanner.nextLine();
            
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();
            
            System.out.print("Enter Problem Description: ");
            String problem = scanner.nextLine();
            
            System.out.print("Enter Consumer ID: ");
            long consumerId = Long.parseLong(scanner.nextLine());
            
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter Mobile Number: ");
            String mobile = scanner.nextLine();

            insertComplaint(type, category, landmark, customerName, problem, consumerId, address, mobile);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number format");
        }
    }

    private void insertComplaint(String type, String category, String landmark,
                               String customerName, String problem, long consumerId,
                               String address, String mobile) throws Exception {
        String sql = "INSERT INTO complaints (complaint_type, category, landmark, customer_name, "
                  + "problem_description, consumer_id, address, mobile_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, type);
            pstmt.setString(2, category);
            pstmt.setString(3, landmark);
            pstmt.setString(4, customerName);
            pstmt.setString(5, problem);
            pstmt.setLong(6, consumerId);
            pstmt.setString(7, address);
            pstmt.setString(8, mobile);
            
            pstmt.executeUpdate();
            System.out.println("Successfully registered Complaint");
        }
    }

    public static void main(String[] args) {
        ComplaintRegistration registration = new ComplaintRegistration();
        try {
            registration.createComplaintTable();
            registration.registerComplaint();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            registration.scanner.close();
        }
    }
}