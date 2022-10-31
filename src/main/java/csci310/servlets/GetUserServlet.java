package csci310.servlets;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import csci310.Database;

public class GetUserServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		// Get parameters from POST
        String username=request.getParameter("username");
        
        // Set up db
        String dbURL = "groupdate";
		Database db = new Database();	
        Database.connect(dbURL);
        ArrayList<String> user = db.getUser(username);

        // Create response
        PrintWriter out = response.getWriter();
	    String json = new Gson().toJson(user);
	    response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		out.print(json);
        Database.disconnect();
    }
}
