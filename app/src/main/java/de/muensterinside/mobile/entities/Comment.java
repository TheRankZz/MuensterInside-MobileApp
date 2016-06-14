package de.muensterinside.mobile.entities;


import java.util.Date;

/**
 * Datenklasse: Kommentar
 *
 * @author Julia Bracht, Nicolas Burchert, Lennart Giesen, Julius Wessing
 *
 */

public class Comment {

	private String text;
	private Device device;
	private Location location;
	private String date;
	private int id;

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

	public Integer getId() {return id;}

	public void setId(int id) {this.id = id;}

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
		return this.location;
	}

	public String getDate(){ return this.date; }

	public void setDate(String date){ this.date = date; }
}
