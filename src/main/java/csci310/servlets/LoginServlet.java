package csci310.servlets;

import csci310.AuthAPI;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/auth/login")
public class LoginServlet extends HttpServlet{

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
        if(a.verifyCredentials(username, password) != 0) {
            response.setStatus(401);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login Failed");
        }
        else {
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.print("{\"username\":\""+ username+ "\"}");
        }
    }
}
