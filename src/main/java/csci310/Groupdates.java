package csci310;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

// Shape for Response Object
class GroupdateEvents {
    int groupdate_id;
    String title;
    Status status;
    String inviter;
    String date;
    ArrayList<GroupdateEvent> events;
    public GroupdateEvents(Groupdate groupdate, ArrayList<GroupdateEvent> events) {
        this.groupdate_id = groupdate.groupdate_id;
        this.title = groupdate.title;
        this.status = groupdate.status;
        this.date = groupdate.date;
        this.inviter = groupdate.inviter;
        this.events = events;
    }
}

public class Groupdates {

    public String dbURL = "groupdate";

    public String getGroupDates(String username, String role) {
        // List
        ArrayList<Groupdate> results = new ArrayList<Groupdate>();
        // Connect to database
        Database db = new Database();
        Database.connect(dbURL);
        // Get Groupdates from Backend
        results = db.getGroupdates(username, role);
        // Disconnect database
        Database.disconnect();
        // Serialize ArrayList
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        String jsonString = gsonObject.toJson(results);
        // Return
        return jsonString;
    }

    public String getEvents(int groupdate_id) {
        // List
        ArrayList<GroupdateEvent> results = new ArrayList<GroupdateEvent>();
        Groupdate ge = null;
        // Connect to database
        Database db = new Database();
        Database.connect(dbURL);
        // Get GroupdateEvents from Backend
        results = db.getGroupdateEvents(groupdate_id);
        // Get Groupdate Info from Backend
        ge = db.getGroupdate(groupdate_id);
        // Disconnect database
        Database.disconnect();
        // Make response object
        GroupdateEvents groupdateEvents = new GroupdateEvents(ge, results);
        // Serialize response object
        GsonBuilder gsonMapBuilder = new GsonBuilder();
        Gson gsonObject = gsonMapBuilder.create();
        String jsonString = gsonObject.toJson(groupdateEvents);
        // Return
        return jsonString;
    }
}
