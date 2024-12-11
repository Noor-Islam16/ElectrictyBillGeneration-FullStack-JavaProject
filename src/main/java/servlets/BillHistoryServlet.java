package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.UsageHistory;
import services.BillHistoryService;
import services.CustomerService;

@WebServlet("/billHistory")
public class BillHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            BillHistoryService historyService = new BillHistoryService();
            CustomerService customerService = new CustomerService();
            
            // Get consumer ID from user ID
            String username = customerService.getCustomerName(userId);
            List<UsageHistory> usageHistory = historyService.getUsageHistory(userId);
            
            // Calculate summary statistics
            double averageUsage = 0;
            String highestUsageMonth = "";
            String lowestUsageMonth = "";
            
            if (!usageHistory.isEmpty()) {
                averageUsage = historyService.calculateAverageUsage(usageHistory);
                highestUsageMonth = historyService.getHighestUsageMonth(usageHistory);
                lowestUsageMonth = historyService.getLowestUsageMonth(usageHistory);
            }
            
            // Set attributes
            request.setAttribute("username", username);
            request.setAttribute("usageHistory", usageHistory);
            request.setAttribute("averageUsage", String.format("%.2f", averageUsage));
            request.setAttribute("highestUsageMonth", highestUsageMonth);
            request.setAttribute("lowestUsageMonth", lowestUsageMonth);
            
            request.getRequestDispatcher("billHistory.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home.jsp?error=true");
        }
    }
}