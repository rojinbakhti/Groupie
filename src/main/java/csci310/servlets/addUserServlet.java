package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import csci310.*;

class AddUserBody {
    public String username;
    public String user;
    public ArrayList<EventDetails> events;

}
public class addUserServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddUserBody res = new Gson().fromJson(request.getReader(), AddUserBody.class);
        // Set up db
        String dbURL = "groupdate";
        Database db = new Database();
        Database.connect(dbURL);
        System.out.println("user:"+res.user);
        System.out.println("events:"+res.events);
        Boolean check = true;

//                        // get unavailabilities of the user
//                        // for each event
//                             for each unavailibitites
//                                bool = unavailability_starttime <= event start time  <= unavailability_endtime
//                                if bool == false:
//                                    return false
//
//                           return true
//


        String user_availability = db.getAvailability(res.user);
        Availability availability = new Availability();
        availability.readAvailability(user_availability);
        String inviter_blockedlist = db.getBlockList(res.username);
        System.out.println("inviterBlocklist:"+inviter_blockedlist);
        System.out.println("user_availability:"+user_availability);
        if (inviter_blockedlist != null){
            check = !inviter_blockedlist.contains(res.user);
        }
       for(EventDetails event: res.events){
           for(Slot slot: availability.availabilities){
               DateTimeFormatter formatter =
                       DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
               LocalDateTime unavailability_startTime = LocalDateTime.parse(slot.startTime.toString(), formatter);
               LocalDateTime unavailability_endTime = LocalDateTime.parse(slot.endTime.toString(), formatter);
               LocalDate datePart = LocalDate.parse(event.localDate);
               LocalTime timePart = LocalTime.parse(event.localTime);
               LocalDateTime event_dt = LocalDateTime.of(datePart, timePart);
               System.out.println("event_dt is:"+ event_dt.format(formatter));
               System.out.println("firstCondition:"+ event_dt.isAfter(unavailability_endTime));
               System.out.println("secondCondition:"+ event_dt.isBefore(unavailability_startTime));
               if(!(event_dt.isAfter(unavailability_endTime) || event_dt.isBefore(unavailability_startTime))) {
                  System.out.println("event that violates"+event_dt.toString());
                   check = false;
               }

           }
       }
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.print(check);
        Database.disconnect();
    }
}
