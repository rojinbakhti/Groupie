package csci310;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;



/**
 * TicketMaster API Data Model
 */
class Attraction{
    public String href;
    public String name;
    public String type;
    public String id;
    public boolean test;
    public String url;
    public String locale;
   
}

class Start{
	public String localDate;
	public String localTime;
	public String dateTime;
	public boolean dateTBD;
	public boolean dateTBA;
	public boolean timeTBA;
	public boolean noSpecificTime;
}

class Dates{
	public Start start;
	public String timezone;
	public boolean spanMultipleDays;
}

class GeneralInfo{
    public String generalRule;
    public String childRule;
}

class Venue{
	public String href;
	public String name;
	public String type;
	public String id;
	public String url;
	public String locale;
	public String postalCode;
	public String timezone;
	public City city;
	public State state;
	public Country country;
	public Address address;
	public GeneralInfo generalInfo;
}

class City{
	public String name;
}

class State{
	public String name;
	public String stateCode;
}

class Country{
	public String name;
	public String countryCode;
}

class Address{
	public String line1;
}

class Embedded{
	public List<Venue> venues;
	public List<Attraction> attractions;
	public List<Event> events;
}

class PriceRanx{
    public String type;
    public String currency;
    public double min;
    public double max;
}

class Event{
	public String name;
	public String type;
	public String id;
	public String url;
	public String locale;
	public Dates dates;
	public String info;
	public String pleaseNote;
	public Embedded _embedded;
	public List<PriceRanx> priceRanges;
}

class Root{
	public Embedded _embedded;
}



public class TicketmasterAPI {
	// Static constants
	private static final String apikey = "qfTCb26bEwvCbbeVQBXI1oGMwwzKTya7";
	private static final String apikeyBackup = "VsGcB2PK7xnb4M2nnsG3qZTYEK35ukAi";
	private static final int numResults = 10; // default num pages returned is 20
	private static final String countryCode = "US"; // ask later if this can be assumed
	private static final String baseUrl = String.format(
			"https://app.ticketmaster.com/discovery/v2/events?apikey=%s&size=%d&country=%s",
			apikey, numResults, countryCode);

	// Static data structures
	Map<String, String> eventDetails;
	ArrayList<EventDetails> events = new ArrayList<EventDetails >();

	/**
	 * Populates events details with the provided JSON object in the class variables
	 *
	 * @param json object with event and venue information from TicketMaster API
	 */
	void setEventDetails(Root json){
		if (json._embedded!= null) {
			for (Event event : json._embedded.events) {
				String name =  event.name;
				String url = event.url;
				String localDate = event.dates.start.localDate;
				String localTime = event.dates.start.localTime;
				String venueName = event._embedded.venues.get(0).name;
				String venueCity = event._embedded.venues.get(0).city.name;
				String venueState = "";
				String venueCountry = event._embedded.venues.get(0).country.countryCode;
				String venueAddress = event._embedded.venues.get(0).address.line1;
				String info = "";

				if (event.info!= null) {
					info = event.info;
				}
				
				if (event._embedded.venues.get(0).state != null) {
					venueState = event._embedded.venues.get(0).state.stateCode;		
				}

				EventDetails eventDetails = new EventDetails(name, url, localDate, localTime, venueName, venueCity, venueState, venueCountry, venueAddress, info);
				events.add(eventDetails);
			}
		}
	}

	/**
	 * Returns a string URL for the event query
	 *
	 * @param info event details
	 * @return string URL
	 */
	String getUrl(Map<String, String> info) {
		String url = baseUrl;
		for (String key: info.keySet()) {
			if (!info.get(key).equals("")){
				url += String.format("&%s=%s", key, info.get(key));
			}
		}
		return url;
	}

	/**
	 * Searches and returns events that meet search criteria
	 *
	 * @param info object with event and venue information from TicketMaster API
	 * @return ArrayList<Map<String, String>> of the events that match the provided eventDetails
	 * @throws IOException
	 */
	public ArrayList<EventDetails > searchEvents(Map<String, String> info) throws IOException{
		URL url = new URL(getUrl(info));
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = in.readLine();
		Root json = new Gson().fromJson(inputLine, Root.class);
		setEventDetails(json);
		
		return events;
	}
}
