package services;
import java.sql.*;
import java.util.Scanner;
import database.DatabaseConnection;
import models.Customer;

public class CustomerRegistration {
    
    public void registerCustomer(Customer customer) throws Exception {
        String sql = "INSERT INTO customers (consumer_id, bill_number, title, customer_name, "
                + "email, mobile_number, user_id, password, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, customer.getConsumerId());
            pstmt.setInt(2, customer.getBillNumber());
            pstmt.setString(3, customer.getTitle());
            pstmt.setString(4, customer.getCustomerName());
            pstmt.setString(5, customer.getEmail());
            pstmt.setString(6, customer.getMobileNumber());
            pstmt.setString(7, customer.getUserId());
            pstmt.setString(8, customer.getPassword());
            pstmt.setString(9, "Active");
            
            pstmt.executeUpdate();
            System.out.println("Customer Registration is successful");
        }
    }

    public Customer inputCustomerDetails() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        
        System.out.println("Enter Customer Details:");
        
        System.out.print("Enter Consumer ID (13 digits): ");
        customer.setConsumerId(scanner.nextLong());
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter Bill Number (5 digits): ");
        customer.setBillNumber(scanner.nextInt());
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter Title (Mr/Ms/Mrs): ");
        customer.setTitle(scanner.nextLine());
        
        System.out.print("Enter Customer Name: ");
        customer.setCustomerName(scanner.nextLine());
        
        System.out.print("Enter Email: ");
        customer.setEmail(scanner.nextLine());
        
        System.out.print("Enter Mobile Number (10 digits): ");
        customer.setMobileNumber(scanner.nextLine());
        
        System.out.print("Enter User ID (min 5, max 20 characters): ");
        customer.setUserId(scanner.nextLine());
        
        System.out.print("Enter Password: ");
        customer.setPassword(scanner.nextLine());
        scanner.close();
        
        return customer;
    }
    
    public static void main(String[] args) {
        CustomerRegistration registration = new CustomerRegistration();
        
        try {
            Customer customer = registration.inputCustomerDetails();
            registration.registerCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}