package de.muensterinside.mobile.entities;

/**
 *
 *
 * @author Julia Bracht, Nicolas Burchert, Lennart Giesen, Julius Wessing
 *
 */


public class Image {

	private String mimeType;
	private byte[] content;
	private Location location;


	public Image() { }

	public Image(byte[] imageData, String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location){
		this.location = location;
	}

}
