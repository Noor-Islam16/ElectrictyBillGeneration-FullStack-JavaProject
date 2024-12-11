package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Bill;
import services.BillService;

@WebServlet("/viewBill")
public class ViewBillServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            BillService billService = new BillService();
            List<Bill> bills = billService.getBillsForUser(userId);
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("viewBill.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home.jsp?error=true");
        }
    }
}