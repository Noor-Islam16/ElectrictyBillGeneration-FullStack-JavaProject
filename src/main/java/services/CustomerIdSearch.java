package services;
import java.sql.*;
import java.util.Scanner;
import database.DatabaseConnection;

public class CustomerIdSearch {
    private Scanner scanner = new Scanner(System.in);

    public void searchById() throws Exception {
        try {
            System.out.print("Enter Consumer ID to search: ");
            long consumerId = Long.parseLong(scanner.nextLine());
            
            String sql = "SELECT * FROM customers WHERE consumer_id = ?";
            
            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setLong(1, consumerId);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    System.out.println("\nCustomer found:");
                    System.out.println("ID: " + rs.getLong("consumer_id"));
                    System.out.println("Name: " + rs.getString("customer_name"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Mobile: " + rs.getString("mobile_number"));
                } else {
                    System.out.println("No Such Customer Exists");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid consumer ID");
        }
    }

    public static void main(String[] args) {
        CustomerIdSearch search = new CustomerIdSearch();
        try {
            search.searchById();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            search.scanner.close();
        }
    }
}