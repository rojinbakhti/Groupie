package csci310.servlets;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.crypto.Data;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import csci310.Database;
import csci310.EventDetails;
import csci310.Groupdate;
import csci310.Status;

class PGD{
    public String username;
    public String title;
    public String date;
    public String action;
    public ArrayList<EventDetails> events;
    public ArrayList<String> users;

}
public class ProposeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get parameters from POST
//        String username = request.getParameter("username");
//        String users = request.getParameter("users");
//        String date = request.getParameter("date");
//        String title = request.getParameter("title");
//        String events = request.getParameter("events");
//        System.out.println(users);
//        System.out.println(title);
//        System.out.println(events);
        PGD res = new Gson().fromJson(request.getReader(), PGD.class);
        System.out.println(res.users);
        System.out.println(res.title);
        System.out.println(res.events);
        System.out.println(res.date);
        System.out.println(res.action);
        // Set up db
        String dbURL = "groupdate";
        Database db = new Database();
        Database.connect(dbURL);
        // CHECK THE LAST PGD ROW IN THE GROUPDATE TABLE AND GET THE GROUPDATE_ID
        int latest_groupdate_id = db.getLatestGroupID();
        // INCREMENT GROUPDATE_ID
        int new_groupdate_id = latest_groupdate_id + 1;
        // CREATE PGD ROWS FOR ALL INVITER & INVITEE COMBINATIONS
        //res.users = res.users.substring(1, res.users.length() - 1);
        //String[] users_arr = res.users.split(",");

        for(String user: res.users){
            if (res.action.equals("propose")){
                System.out.println(db.addPGD(new_groupdate_id,res.username,user, Status.inviter_unfinalized,res.title,res.date));
            }
            if (res.action.equals("draft")){
                System.out.println(db.addPGD(new_groupdate_id,res.username,user, Status.inviter_drafted,res.title,res.date));
            }

        }
        // CREATE EVENT ROWS FOR ALL THE EVENTS IN THE PGD
        for (int j = 0; j < res.events.size(); j++){
            String json = new Gson().toJson(res.events.get(j));
            int last_event_id = db.addEvent(json,new_groupdate_id);
            // FOR EACH EVENT_ID CREATE ROWS IN THE USER_RESPONSE TABLE FOR ALL THE INVITEES
            for(String user: res.users){
                db.addUserResponse(last_event_id,user,"none",0);
            }
        }
        Database.disconnect();
        // Create response
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        //response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("success");

    }

}









