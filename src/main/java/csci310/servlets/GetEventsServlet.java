package csci310.servlets;

import csci310.Groupdates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetEventsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        int groupdate_id = Integer.parseInt(request.getParameter("groupdate_id"));
        Groupdates gd = new Groupdates();
        String result = gd.getEvents(groupdate_id);
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
