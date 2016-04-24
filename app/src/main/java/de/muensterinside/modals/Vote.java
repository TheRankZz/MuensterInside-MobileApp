package de.muensterinside.modals;

import java.io.Serializable;

public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;

	private int locationId;
	
	private String deviceId;
	
	/* Beziehungen */
	
	private VoteType type;
	
	private Location location;
	

	/**
	 * 
	 * @param locationId
	 * @param deviceId
	 * @param location
	 * @param type
	 */
	public Vote(int locationId, String deviceId, Location location, VoteType type) {
		this.locationId = locationId;
		this.deviceId = deviceId;
		this.location = location;
		this.type = type;
	}
	
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
	 * @return the type
	 */
	public VoteType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(VoteType type) {
		this.type = type;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}	
	
}
