package csci310;

import java.util.ArrayList;

public class User {
	String id;
	String username;
	String availabilties;
	public User(String id, String username, String availabilties) {
		this.id = id;
		this.username = username;
		this.availabilties = availabilties;
	}
	String getId() {
		return id;
	}
	String getUsername() {
		return username;
	}
	String getAvailabilities() {
		return availabilties;
	}
}
