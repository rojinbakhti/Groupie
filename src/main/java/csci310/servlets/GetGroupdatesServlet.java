package csci310.servlets;

import csci310.Groupdates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetGroupdatesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        Groupdates gd = new Groupdates();
        String result = gd.getGroupDates(username, role);
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print(result);
    }
}
