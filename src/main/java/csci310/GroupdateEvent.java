package csci310;

import java.util.ArrayList;
public class GroupdateEvent {
    int event_id;
    String info;
    ArrayList<Response> user_responses;
    public GroupdateEvent() {
        // Do Nothing
    }
    public GroupdateEvent(int event_id, String info) {
        this.event_id = event_id;
        this.info = info;
        this.user_responses = null;
    }
    public void setUserResponse(ArrayList<Response> user_responses_){
        user_responses = user_responses_;
    }
}