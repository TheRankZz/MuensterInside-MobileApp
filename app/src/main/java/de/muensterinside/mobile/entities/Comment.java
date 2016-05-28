package de.muensterinside.mobile.entities;



/**
 * Datenklasse: Kommentar
 *
 * @author Lennart Giesen, Julius Wessing
 *
 */

public class Comment {

	private String text;
	private Device device;
	private Location location;

	public Comment() {
	}

	public Comment(String text, Device device, Location location) {
		this.text = text;
		this.device = device;
		this.location = location;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Device getDevice(){
		return this.device;
	}

	public void setDevice(Device device){
		this.device = device;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}
}
