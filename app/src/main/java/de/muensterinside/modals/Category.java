package de.muensterinside.modals;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	/* Beziehungen */
	
	private List<Location> locations;
	
	/**
	 * @param name
	 */
	public Category(String name) {
		this.setName(name);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}
}
