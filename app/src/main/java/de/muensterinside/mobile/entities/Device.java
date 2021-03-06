package de.muensterinside.mobile.entities;

import java.util.List;


/**
 * Datenklasse: Bewertung/Stimme
 * 
 * @author Julia Bracht, Nicolas Burchert, Lennart Giesen, Julius Wessing
 *
 */

public class Device {


	private String androidUuid;
	private String username;
	private int id;

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

}
