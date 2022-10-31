package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import csci310.Availability;
import csci310.Database;
import csci310.Status;

public class DecisionServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		// Get parameters from POST
        String decision = request.getParameter("decision");
        String groupdate_id = request.getParameter("groupdate_id");
        String invitee_username = request.getParameter("username");
        // Set up db
        String dbURL = "groupdate";
		Database db = new Database();	
        Database.connect(dbURL);
        System.out.println(decision);
        System.out.println(groupdate_id);
        if (decision.equals("accept")) {
                System.out.println(db.updatePGDStatus_invitee(Integer.parseInt(groupdate_id), Status.invitee_accepted,invitee_username)+"updated pgd status to accepted");
                // update event's count of acceptances
                // db.updateFinalizedPGD("accept");
                //add event to invitee's unavailability
                String availability = db.getAvailability(invitee_username);
                // get event_id of the finalized PGD
                // event_id = db.getEventID_finalizedPGD()
                // get info of this event_id
                // parse info into event details
                // startTime = get startTime
                // endTime = startTime
                // event = get title
                // Availability new_availability = new Availability();
                // new_availability.readAvailability(availability);
                // new_availability.add();
                // db.changeAvailability(username,new_availability.saveAvailability());

        }
        if (decision.equals("decline")) {
            db.updatePGDStatus_invitee(Integer.parseInt(groupdate_id),Status.invitee_declined,invitee_username);
                //update event's count of rejected's
                // db.updateFinalizedPGD("reject");
                // get event_id of the finalized PGD
                // event_id = db.getEventID_finalizedPGD()
                // get info of this event_id
                // parse info into event details
                // startTime = get startTime
                // endTime = startTime + 1
                // event = get title
                // Availability new_availability = new Availability();
                // new_availability.readAvailability(availability);
                // new_availability.add();
                // db.changeAvailability(username,new_availability.saveAvailability());

        }

        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print("{\"success\":\"true\"}");
        Database.disconnect();
    }
}
