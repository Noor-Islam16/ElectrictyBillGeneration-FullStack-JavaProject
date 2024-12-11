package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import models.Complaint;
import services.ComplaintService;

@WebServlet("/checkStatus")
public class CheckStatusServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String complaintId = request.getParameter("complaintId");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            ComplaintService service = new ComplaintService();
            Complaint complaint = service.getComplaintStatus(complaintId);
            
            JSONObject jsonResponse = new JSONObject();
            
            if (complaint != null) {
                jsonResponse.put("found", true);
                jsonResponse.put("complaintId", complaint.getComplaintId());
                jsonResponse.put("type", complaint.getComplaintType());
                jsonResponse.put("category", complaint.getCategory());
                jsonResponse.put("description", complaint.getDescription());
                jsonResponse.put("address", complaint.getAddress());
                jsonResponse.put("contactNumber", complaint.getContactNumber());
                jsonResponse.put("contactPerson", complaint.getContactPerson());
                // Only add these if they exist in your schema
                // jsonResponse.put("status", complaint.getStatus());
                // jsonResponse.put("registrationDate", complaint.getRegistrationDate());
                // jsonResponse.put("lastUpdated", service.getLastUpdateDate(complaintId));
            } else {
                jsonResponse.put("found", false);
                jsonResponse.put("message", "No complaint found with ID: " + complaintId);
            }
            
            out.print(jsonResponse.toString());
            
        } catch (Exception e) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error checking complaint status");
            errorResponse.put("message", e.getMessage());
            out.print(errorResponse.toString());
            e.printStackTrace();
        } finally {
            out.flush();
        }
    }
}