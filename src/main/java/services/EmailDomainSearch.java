package services;
import java.sql.*;
import java.util.Scanner;
import database.DatabaseConnection;

public class EmailDomainSearch {
    private Scanner scanner = new Scanner(System.in);

    public void searchByDomain() throws Exception {
        System.out.print("Enter email domain (e.g., gmail.com): ");
        String domain = scanner.nextLine();
        
        String sql = "SELECT consumer_id, customer_name, email FROM customers WHERE email LIKE ? ORDER BY consumer_id";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + domain);
            ResultSet rs = pstmt.executeQuery();
            
            boolean found = false;
            System.out.println("\nSearch Results:");
            
            while (rs.next()) {
                found = true;
                System.out.println("\nCustomer ID: " + rs.getLong("consumer_id"));
                System.out.println("Name: " + rs.getString("customer_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---------------");
            }
            
            if (!found) {
                System.out.println("No customers found with email domain: " + domain);
            }
        }
    }

    public static void main(String[] args) {
        EmailDomainSearch search = new EmailDomainSearch();
        try {
            search.searchByDomain();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            search.scanner.close();
        }
    }
}