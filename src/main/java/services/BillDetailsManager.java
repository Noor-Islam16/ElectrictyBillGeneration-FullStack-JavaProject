package services;
import java.sql.*;
import java.util.Scanner;
import database.DatabaseConnection;

public class BillDetailsManager {
    private Scanner scanner = new Scanner(System.in);

    public void createBillTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS bills ("
            + "bill_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "consumer_id BIGINT,"
            + "month VARCHAR(10),"              // Added for month
            + "units_consumed DECIMAL(10,2),"   // Added for electricity usage
            + "due_amount DECIMAL(10,2),"
            + "payable_amount DECIMAL(10,2),"
            + "due_date DATE,"
            + "status VARCHAR(20) DEFAULT 'Unpaid',"
            + "FOREIGN KEY (consumer_id) REFERENCES customers(consumer_id))";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Bills table created");
        }
    }

    public void addBill() throws Exception {
        try {
            System.out.println("\nEnter Bill Details:");
            
            System.out.print("Enter Consumer ID: ");
            long consumerId = Long.parseLong(scanner.nextLine());
            
            System.out.print("Enter Month (e.g., Dec 2024): ");
            String month = scanner.nextLine();
            
            System.out.print("Enter Units Consumed: ");
            double unitsConsumed = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Due Amount: ");
            double dueAmount = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Enter Due Date (YYYY-MM-DD): ");
            String dueDate = scanner.nextLine();

            String sql = "INSERT INTO bills (consumer_id, month, units_consumed, due_amount, payable_amount, due_date) "
                      + "VALUES (?, ?, ?, ?, ?, ?)";
            
            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, consumerId);
                pstmt.setString(2, month);
                pstmt.setDouble(3, unitsConsumed);
                pstmt.setDouble(4, dueAmount);
                pstmt.setDouble(5, dueAmount);
                pstmt.setString(6, dueDate);
                pstmt.executeUpdate();
                System.out.println("Bill added successfully");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number format");
        }
    }

    public static void main(String[] args) {
        BillDetailsManager manager = new BillDetailsManager();
        try {
            manager.createBillTable();
            
            System.out.print("How many bills do you want to add? ");
            int count = Integer.parseInt(manager.scanner.nextLine());
            
            for(int i = 0; i < count; i++) {
                System.out.println("\nBill #" + (i+1));
                manager.addBill();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.scanner.close();
        }
    }
}