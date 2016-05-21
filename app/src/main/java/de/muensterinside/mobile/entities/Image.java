package de.muensterinside.mobile.entities;




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

}
