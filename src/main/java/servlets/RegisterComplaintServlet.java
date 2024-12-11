package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Complaint;
import services.ComplaintService;

@WebServlet("/registerComplaint")
public class RegisterComplaintServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        request.getRequestDispatcher("/registerComplaint.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Create and populate the complaint object
            Complaint complaint = new Complaint();
            complaint.setUserId(userId);
            complaint.setComplaintType(request.getParameter("complaintType"));
            complaint.setCategory(request.getParameter("category"));
            complaint.setContactPerson(request.getParameter("contactPerson"));
            complaint.setContactNumber(request.getParameter("contactNumber"));
            complaint.setDescription(request.getParameter("description"));
            complaint.setAddress(request.getParameter("address"));
            
            // Register the complaint and get the ID
            ComplaintService complaintService = new ComplaintService();
            long complaintId = complaintService.registerComplaint(complaint);
            
            // Format the complaint ID with CMP prefix and leading zeros
            request.setAttribute("complaintId", "CMP" + String.format("%08d", complaintId));
            
            // Forward to success page
            request.getRequestDispatcher("/complaintSuccess.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/registerComplaint.jsp?error=true");
        }
    }
}