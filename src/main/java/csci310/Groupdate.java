package csci310;

import java.util.ArrayList;


// Event class

public class Groupdate {
    int groupdate_id;
    String title;
    Status status;
    String inviter;
    String date;
    ArrayList<EventDetails> events;

    public Groupdate() {
        // Do Nothing
    }

    public Groupdate(int groupdate_id, String title, Status status, String date, String inviter) {
        this.groupdate_id = groupdate_id;
        this.title = title;
        this.status = status;
        this.date = date;
        this.inviter = inviter;
    }

    public ArrayList<EventDetails>  getGroupdateEvents(){
        return events;
    }
    public void setGroupdateEvents(ArrayList<EventDetails> events_){
        events = events_;
    }
    public void setStatus(Status status_){
        status = status_;
    }

}
