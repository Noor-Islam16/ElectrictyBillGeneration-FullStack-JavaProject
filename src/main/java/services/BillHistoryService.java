package services;

import java.sql.*;
import java.util.*;
import models.UsageHistory;
import database.DatabaseConnection;

public class BillHistoryService {
    
    public List<UsageHistory> getUsageHistory(String userId) throws Exception {
        List<UsageHistory> history = new ArrayList<>();
        
        String sql = "SELECT b.month, b.units_consumed, b.due_amount as amount, " +
                    "b.due_date, b.status " +
                    "FROM bills b " +
                    "JOIN customers c ON b.consumer_id = c.consumer_id " +
                    "WHERE c.user_id = ? " +
                    "ORDER BY b.due_date DESC LIMIT 6";
                    
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                history.add(new UsageHistory(
                    rs.getString("month"),
                    rs.getDouble("units_consumed"),
                    rs.getDouble("amount"),
                    rs.getString("due_date"),
                    rs.getString("status") != null ? rs.getString("status") : "Unpaid"
                ));
            }
        }
        return history;
    }
    
    public double calculateAverageUsage(List<UsageHistory> history) {
        if (history.isEmpty()) return 0.0;
        
        double sum = 0.0;
        for (UsageHistory usage : history) {
            sum += usage.getUnitsConsumed();
        }
        return sum / history.size();
    }
    
    public String getHighestUsageMonth(List<UsageHistory> history) {
        if (history.isEmpty()) return "N/A";
        
        UsageHistory highest = history.get(0);
        for (UsageHistory usage : history) {
            if (usage.getUnitsConsumed() > highest.getUnitsConsumed()) {
                highest = usage;
            }
        }
        return highest.getMonth();
    }
    
    public String getLowestUsageMonth(List<UsageHistory> history) {
        if (history.isEmpty()) return "N/A";
        
        UsageHistory lowest = history.get(0);
        for (UsageHistory usage : history) {
            if (usage.getUnitsConsumed() < lowest.getUnitsConsumed()) {
                lowest = usage;
            }
        }
        return lowest.getMonth();
    }
}