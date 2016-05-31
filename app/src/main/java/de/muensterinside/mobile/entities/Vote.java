package de.muensterinside.mobile.entities;



public class Vote {

	private VoteType type;
	private Location location;
	private Device device;

	public Vote() {
	}

	public Vote(Location location, Device device, VoteType type) {
		this.location = location;
		this.device = device;
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public VoteType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
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

	public void setLocation(Location location){
		this.location = location;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device){
		this.device = device;
	}

}
