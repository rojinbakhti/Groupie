package csci310.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FirstServlet extends HttpServlet {
	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		response.getWriter().print("Hello from the Java Web Service Post");
	}
	
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		response.getWriter().print("Hello from the Java Web Service Get");
	}
}
