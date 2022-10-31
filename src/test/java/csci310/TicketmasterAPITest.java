package csci310;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class TicketmasterAPITest {
	TicketmasterAPI t;
	@Before
	public void setUp() throws Exception {
		t = new TicketmasterAPI();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSetEventDetails() throws IOException {
		URL url = new URL("https://app.ticketmaster.com/discovery/v2/events?apikey=qfTCb26bEwvCbbeVQBXI1oGMwwzKTya7&size=1&country=US&startDateTime=2021-10-02T15:00:00Z&city=Los Angeles&classificationName=music&stateCode=CA");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = in.readLine();
		Root json = new Gson().fromJson(inputLine, Root.class);
		ArrayList<EventDetails> events = t.events;

		t.setEventDetails(json);
		assertEquals(events.size(), 1);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void testSearchEvents() throws IOException {
		try {
			Map<String, String> hm = new HashMap<String, String>()
			{{
				put("classificationName", "music");
				put("keyword", "");
				put("startDateTime", "2021-10-02T15:00:00Z");
				put("endDateTime", "");
				put("city", "Los Angeles");
				put("stateCode", "CA");
			}};
			ArrayList<EventDetails> events = t.searchEvents(hm);
			assertTrue(events.size()>1);
			hm = new HashMap<String, String>()
			{{
				put("classificationName", "music");
				put("keyword", "maroon5");
				put("startDateTime", "");
				put("endDateTime", "");
				put("city", "");
				put("stateCode", "CA");
			}};
			events = t.searchEvents(hm);
			assertTrue(events.size()>1);
			hm = new HashMap<String, String>()
			{{
				put("classificationName", "");
				put("keyword", "bts");
				put("startDateTime", "");
				put("endDateTime", "");
				put("city", "");
				put("stateCode", "");
			}};
			events = t.searchEvents(hm);
			assertTrue(events.size()>1);
		} catch(IOException e){
			System.out.println("Rate Limit Exceeded: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetUrl() {
		Map<String, String> hm = new HashMap<String, String>();
		assertEquals(t.getUrl(hm), "https://app.ticketmaster.com/discovery/v2/events?apikey=qfTCb26bEwvCbbeVQBXI1oGMwwzKTya7&size=10&country=US");

	    hm.put("classificationName", "music");
	    hm.put("keyword", "");
	    hm.put("startDateTime", "2021-10-02T15:00:00Z");
	    hm.put("endDateTime", "");
	    hm.put("city", "Los Angeles");
	    hm.put("stateCode", "CA");
	    assertEquals(t.getUrl(hm), "https://app.ticketmaster.com/discovery/v2/events?apikey=qfTCb26bEwvCbbeVQBXI1oGMwwzKTya7&size=10&country=US&startDateTime=2021-10-02T15:00:00Z&city=Los Angeles&classificationName=music&stateCode=CA");
	}

}
