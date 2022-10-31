package csci310;

import org.junit.Test;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupdateTest {


    @Test
    public void testgetGroupdateEvents() {

        Groupdate gd = new Groupdate();
        EventDetails event1 = new EventDetails("name", "url", "localDate", "localTime", "venueName", "venueCity", "venueState", "venueCountry", "venueAddress", "info");
        EventDetails event2 = new EventDetails("name", "url", "localDate", "localTime", "venueName", "venueCity", "venueState", "venueCountry", "venueAddress", "info");
        EventDetails event3 = new EventDetails("name", "url", "localDate", "localTime", "venueName", "venueCity", "venueState", "venueCountry", "venueAddress", "info");

        ArrayList<Response> user_responses = new ArrayList<Response>();
        Response res1 = new Response("rojin","true",5);
        Response res2 = new Response("rojin","true",5);
        user_responses.add(res1);
        user_responses.add(res2);
       // event1.setUserResponse(user_responses);
        ArrayList<EventDetails> events = new ArrayList<EventDetails>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        gd.setStatus(Status.inviter_unfinalized);
        gd.setGroupdateEvents(events);
        assertTrue(gd.getGroupdateEvents().size() > 1);

    }

    @Test
    public void testsetGroupdateEvents() {

        Groupdate gd = new Groupdate();
        EventDetails event1 = new EventDetails("name", "url", "localDate", "localTime", "venueName", "venueCity", "venueState", "venueCountry", "venueAddress", "info");
        EventDetails event2 = new EventDetails("name", "url", "localDate", "localTime", "venueName", "venueCity", "venueState", "venueCountry", "venueAddress", "info");
        EventDetails event3 = new EventDetails("name", "url", "localDate", "localTime", "venueName", "venueCity", "venueState", "venueCountry", "venueAddress", "info");

        ArrayList<Response> user_responses = new ArrayList<Response>();
        Response res1 = new Response("rojin","true",5);
        Response res2 = new Response("rojin","true",5);
        user_responses.add(res1);
        user_responses.add(res2);
        //event1.setUserResponse(user_responses);
        ArrayList<EventDetails> events = new ArrayList<EventDetails>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        gd.setGroupdateEvents(events);
        assertTrue(gd.getGroupdateEvents().size() > 1);
    }
}