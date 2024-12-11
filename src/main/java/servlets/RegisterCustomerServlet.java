package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Customer;
import services.CustomerRegistration;

@WebServlet("/registerCustomer")
public class RegisterCustomerServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Customer customer = new Customer();
            customer.setConsumerId(Long.parseLong(request.getParameter("consumerId")));
            customer.setBillNumber(Integer.parseInt(request.getParameter("billNumber")));
            customer.setTitle(request.getParameter("title"));
            customer.setCustomerName(request.getParameter("customerName"));
            customer.setEmail(request.getParameter("email"));
            customer.setMobileNumber(request.getParameter("countryCode") + 
                                   request.getParameter("mobileNumber"));
            customer.setUserId(request.getParameter("userId"));
            customer.setPassword(request.getParameter("password"));
            customer.setStatus("Active");

            CustomerRegistration registration = new CustomerRegistration();
            registration.registerCustomer(customer);

            // Set attributes for success page
            request.setAttribute("customerId", customer.getConsumerId());
            request.setAttribute("customerName", customer.getCustomerName());
            request.setAttribute("email", customer.getEmail());
            
            // Forward to success page
            request.getRequestDispatcher("registration-success.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registration.jsp?error=true");
        }
    }
}