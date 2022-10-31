package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import csci310.Database;
import csci310.Status;

class EventResponse{
	private int eventId;
	private Boolean eventResponse;
	private int excitement;
 
	public int getEventId() {
		 return eventId;
	}
	
	public Boolean getEventResponse() {
		return eventResponse;
	}
	
	public int getExcitement() {
		return excitement;
	}
}

class GroupDateResponse{
	private String action;
	private String username;
	private String userId;
	private int groupdate_id;
	private ArrayList<EventResponse> events;
	
	public String getAction() {
		return action;
	}
	public int getGroupdateId() {return groupdate_id;}
	public String getUsername() {
		return username;
	}
	public String getUserId() {
		return userId;
	}
	public ArrayList<EventResponse> getEvents() {
		return events;
	}
}
/* Sample Request 
{
    "action":"submit",
    "username": "dasol",
     //we should also have a groupdateid
    "userId": "22",
    "events": [{
            "eventId": "3",
            "eventResponse": "yes",
            "excitement": "5"
    }]
}
 */

public class ResponseServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GroupDateResponse res = new Gson().fromJson(request.getReader(), GroupDateResponse.class);
		String action = res.getAction();
		String userId = res.getUserId();
		int groupdate_id = res.getGroupdateId();
		String username = res.getUsername();
		// Set up db
		String dbURL = "groupdate"; // change to userResponse?
		Database db = new Database();
		Database.connect(dbURL);
		ArrayList<EventResponse> events = res.getEvents();
		for (EventResponse event: events) {
			int eventId = event.getEventId();
			Boolean eventResponse = event.getEventResponse();

			int eventExcitement = event.getExcitement();
			db.updateUserResponse(eventId,username,eventResponse.toString(),eventExcitement);
			// A call to database to update status of the groupdate_id

			System.out.println(eventId);
			System.out.println(eventResponse);
			System.out.println(eventExcitement);
		}
		if(action.equals("submit")){
			db.updatePGDStatus_invitee(groupdate_id, Status.invitee_responded,username);
		}
		if(action.equals("draft")){
			db.updatePGDStatus_invitee(groupdate_id,Status.invitee_drafted,username);
		}
		if(action.equals("finalize")){
			db.updatePGDStatus_inviter(groupdate_id,Status.inviter_finalized,username);
		}
		System.out.println(action);
		System.out.println(userId);
		System.out.println(username);

	    response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print("{\"success\":\"true\"}");
		Database.disconnect();
    }
}
