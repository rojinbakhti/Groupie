package csci310.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import csci310.Database;
import csci310.Groupdate;
import csci310.Response;
import csci310.Status;


public class getUsersResponseServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Response> results = new ArrayList<Response>();
        String event_id = request.getParameter("event_id");
        // db.get_responses:
        Database db = new Database();
        Database.connect("groupdate");
        results = db.getResponses(Integer.parseInt(event_id));
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        String jsonString = gsonObject.toJson(results);
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        Database.disconnect();
    }
}
