package csci310;

public class EventDetails {
	private String name;
	public String url;
	public String localDate;
	public String localTime;
	public String venueName;
	public String venueCity;
	public String venueState;
	public String venueCountry;
	public String venueAddress;
	public String info;
	public EventDetails(String name, String url, String localDate, String localTime, String venueName, String venueCity, String venueState, String venueCountry, String venueAddress, String info) {
		this.name = name;
		this.url = url;
		this.localDate = localDate;
		this.localTime = localTime;
		this.venueName = venueName;
		this.venueCity = venueCity;
		this.venueState = venueState;
		this.venueCountry = venueCountry;
		this.venueAddress = venueAddress;
		this.info = info;
	}
}