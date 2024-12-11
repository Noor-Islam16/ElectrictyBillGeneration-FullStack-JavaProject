package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ComplaintService;

@WebServlet("/serviceDashboard")
public class ServiceDashboardServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String serviceRepId = (String) request.getSession().getAttribute("serviceRepId");
        
        if (serviceRepId == null) {
            response.sendRedirect("serviceLogin.jsp");
            return;
        }
        
        try {
            ComplaintService complaintService = new ComplaintService();
            
            request.setAttribute("totalComplaints", complaintService.getTotalComplaints());
            request.setAttribute("openComplaints", complaintService.getComplaintsByStatus("Open"));
            request.setAttribute("assignedComplaints", complaintService.getComplaintsByStatus("Assigned"));
            request.setAttribute("resolvedComplaints", complaintService.getComplaintsByStatus("Resolved"));
            request.setAttribute("complaints", complaintService.getAllComplaints());
            
            request.getRequestDispatcher("serviceDashboard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("serviceLogin.jsp?error=true");
        }
    }
}