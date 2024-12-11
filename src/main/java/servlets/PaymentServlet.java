package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String[] selectedBills = request.getParameterValues("selectedBills");
            String totalAmount = request.getParameter("totalAmountValue");
            // Store in session
            request.getSession().setAttribute("totalAmount", Double.valueOf(totalAmount));
            request.getRequestDispatcher("payment.jsp").forward(request, response);
            
            if (selectedBills != null && selectedBills.length > 0) {
                request.setAttribute("selectedBills", String.join(", ", selectedBills));
                request.setAttribute("totalAmount", totalAmount);
                
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewBill?error=noSelection");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewBill?error=true");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("viewBill");
    }
}