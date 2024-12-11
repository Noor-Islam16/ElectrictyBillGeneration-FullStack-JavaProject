package models;

public class Bill {
    private long id;
    private long consumerId;
    private String userId;    // Added this field
    private double amount;
    private String date;
    private String dueDate;
    private String status;

    // Existing getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getConsumerId() { return consumerId; }
    public void setConsumerId(long consumerId) { this.consumerId = consumerId; }

    // Add these new getter and setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}