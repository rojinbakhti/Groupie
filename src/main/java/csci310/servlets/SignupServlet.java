package csci310.servlets;

import csci310.AuthAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/auth/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from POST
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        // Create response
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setCharacterEncoding("UTF-8");
        // Verify credentials
        AuthAPI a = new AuthAPI();
        if(a.createUser(username, password) != 0) {
            response.setStatus(401);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Signup Failed");
        }
        else {
            PrintWriter out = response.getWriter();
            response.setStatus(200);
            out.print("{\"username\":\""+ username+ "\"}");
        }
    }
}
