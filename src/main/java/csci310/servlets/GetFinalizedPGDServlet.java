package csci310.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import csci310.Database;
import csci310.FinalizedPGD;
import csci310.Groupdates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;


public class GetFinalizedPGDServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        int groupdate_id = Integer.parseInt(request.getParameter("groupdate_id"));
        Database db = new Database();
        Database.connect("groupdate");
        db.insertFinalizedPGD(8,888);
        FinalizedPGD f = db.getFinalizedPGD(groupdate_id);
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        String jsonString = gsonObject.toJson(f);
        response.setStatus(200);
        Database.disconnect();
        PrintWriter out = response.getWriter();
        out.print(jsonString);
   }
}