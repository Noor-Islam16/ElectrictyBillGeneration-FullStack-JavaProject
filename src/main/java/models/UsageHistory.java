package models;

public class UsageHistory {
    private String month;
    private double unitsConsumed;
    private double amount;
    private String dueDate;
    private String status;
    
    // Constructor
    public UsageHistory(String month, double unitsConsumed, double amount, 
                       String dueDate, String status) {
        this.month = month;
        this.unitsConsumed = unitsConsumed;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    // Getters and setters
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public double getUnitsConsumed() { return unitsConsumed; }
    public void setUnitsConsumed(double unitsConsumed) { this.unitsConsumed = unitsConsumed; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}