package csci310.servlets;
import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import csci310.Database;
import csci310.User;

public class FetchAllUsersServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set up db
        String dbURL = "groupdate";
		Database db = new Database();	
        Database.connect(dbURL);
        ArrayList<User> users = db.fetchAllUsers();
        // Create response
        PrintWriter out = response.getWriter();
	    String json = new Gson().toJson(users);
	    response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		out.print(json);
        Database.disconnect();
    }
}
