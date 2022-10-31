package csci310.servlets;

import com.google.gson.Gson;
import csci310.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

public class AvailabilityServlet extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        Database db = new Database();
        String addSlot = request.getParameter("addSlot");
        String show = request.getParameter("show");
        String username = request.getParameter("username");
        Database.connect("groupdate");
        //System.out.println(userAvailability);
        Availability newAvailability = new Availability();
        String removeSlot = request.getParameter("removeSlot");
        if(show != null && show.equals("true")){
            //db.getAvailability(username);
            PrintWriter out = response.getWriter();
            out.print(db.getAvailability(username));
        }
        else if (addSlot != null && addSlot.equals("true")){
            //System.out.println(Database.connect("groupdate"));
            String userAvailability = db.getAvailability(username);
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            String event = request.getParameter("event");

            newAvailability.readAvailability(userAvailability);
            newAvailability.add(startTime,endTime,event);
            db.changeAvailability(username,newAvailability.saveAvailability());
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.print("slotAdded");

        }
        else if (removeSlot != null && removeSlot.equals("true")){
            //System.out.println(Database.connect("groupdate"));
            String userAvailability = db.getAvailability(username);
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            String event = request.getParameter("event");
            newAvailability.readAvailability(userAvailability);
            newAvailability.remove(startTime,endTime,event);
            db.changeAvailability(username,newAvailability.saveAvailability());
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.print("slotRemoved");

        }
        else{
            response.setStatus(401);
            PrintWriter out = response.getWriter();
            out.print("noAction");
        }
        Database.disconnect();

    }
}
