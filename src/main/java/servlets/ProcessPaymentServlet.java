package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PaymentService;
import java.util.UUID;
import java.time.LocalDateTime;

@WebServlet("/processPayment")
public class ProcessPaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println("ProcessPaymentServlet doPost method called"); 
        String cardNumber = request.getParameter("cardNumber");
        String cardHolderName = request.getParameter("cardHolderName");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");
        
        try {
            // Generate unique transaction ID
            String transactionId = "TXN" + UUID.randomUUID().toString().substring(0, 8);
            LocalDateTime transactionDate = LocalDateTime.now();
            Double totalAmount = (Double) request.getSession().getAttribute("totalAmount");
         // If somehow amount is null, use selected bill amount
         if(totalAmount == null) {
             System.out.println("Warning: Total amount was null in session");
             totalAmount = 1500.00; // Default fallback
         }
            String userId = (String) request.getSession().getAttribute("userId");

            PaymentService paymentService = new PaymentService();
            boolean paymentSuccess = paymentService.processPayment(transactionId, userId, 
                                                                 totalAmount, cardNumber);

            if (paymentSuccess) {
                request.setAttribute("transactionId", transactionId);
                request.setAttribute("transactionDate", transactionDate);
                request.setAttribute("amountPaid", totalAmount);
                request.getRequestDispatcher("paymentSuccess.jsp").forward(request, response);
            } else {
                response.sendRedirect("cardPayment.jsp?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cardPayment.jsp?error=true");
        }
    }
}