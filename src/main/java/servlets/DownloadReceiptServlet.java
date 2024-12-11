package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.PaymentService;

@WebServlet("/downloadReceipt")
public class DownloadReceiptServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String transactionId = request.getParameter("transactionId");
        
        try {
            PaymentService paymentService = new PaymentService();
            String receiptContent = paymentService.generateReceipt(transactionId);

            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", 
                             "attachment; filename=receipt_" + transactionId + ".txt");

            try (PrintWriter writer = response.getWriter()) {
                writer.write(receiptContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paymentSuccess.jsp?error=true");
        }
    }
}