package de.muensterinside.mobile.entities;

import java.util.Date;
import java.util.List;


import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.entities.Vote;

/**
 * Datenklasse: Bewertung/Stimme
 * 
 * @author Lennart Giesen, Julius Wessing
 *
 */

public class Device {


	private String androidUuid;
	private String username;
	private int id;
	private List<Comment> comments;
	private List<Location> locations;
	private List<Vote> votes;

	public Device() {
	}

	public Device(String androidUuid, String username) {
		this.androidUuid = androidUuid;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getAndroidUuid() {
		return androidUuid;
	}

	public void setAndroidUuid(String androidUuid) {
		this.androidUuid = androidUuid;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public List<Vote> getVotes() {
		return votes;
	}


}
