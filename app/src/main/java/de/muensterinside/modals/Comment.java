package de.muensterinside.modals;

import java.io.Serializable;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String text;
	
	private String deviceId;
	
	/* Beziehungen */
	
	private Location location;
		

	/**
	 * 
	 * @param text
	 * @param deviceId
	 * @param location
	 */
	public Comment(String text, String deviceId, Location location) {
		this.text = text;
		this.deviceId = deviceId;
		this.location = location;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
}
