package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ServiceRepresentativeService;

@WebServlet("/serviceLogin")
public class ServiceLoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Debug print
        System.out.println("Received service login request - Username: " + username);
        
        try {
            ServiceRepresentativeService service = new ServiceRepresentativeService();
            
            boolean isValid = service.validateLogin(username, password);
            System.out.println("Login validation result: " + isValid);
            
            if (isValid) {
                HttpSession session = request.getSession();
                session.setAttribute("serviceRepName", username);
                String repId = service.getServiceRepId(username);
                session.setAttribute("serviceRepId", repId);
                response.sendRedirect("serviceDashboard");
            } else {
                System.out.println("Login failed for service rep: " + username);
                response.sendRedirect("serviceLogin.jsp?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("serviceLogin.jsp?error=true");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("serviceLogin.jsp");
    }
}