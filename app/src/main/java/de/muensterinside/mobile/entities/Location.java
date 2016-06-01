package de.muensterinside.mobile.entities;

import java.util.List;


/**
 * Datenklasse: Location
 *
 * @author Lennart Giesen, Julius Wessing
 *
 */

public class Location {

	private int id;
	private String name;
	private String description;
	private String link;
	private int voteValue;
	private Device device;
	private List<Comment> comments;
	private Category category;
	private List<Vote> votes;
	boolean isVoted = false;


	public Location() {
	}

	public Location(int id,String name, String description, String link, Device device, Category category) {
		this.name = name;
		this.description = description;
		this.link = link;
		this.device = device;
		this.category = category;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	/**
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the description
	 */
	public Device getDevice(){
		return this.device;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the voteValue
	 */
	public int getVoteValue() {
		return voteValue;
	}

	/**
	 * @param voteValue
	 *            the voteValue to set
	 */
	public void setVoteValue(int voteValue) {
		this.voteValue = voteValue;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the votes
	 */
	public List<Vote> getVotes() {
		return votes;
	}

	public boolean isVoted(){
		return this.isVoted;
	}

	/**
	 * Zählt den VoteValue um eins hoch
	 *
	 * @return the new VoteValue
	 */
	public int upVote() {
		int temp = this.getVoteValue() + 1;
		this.setVoteValue(temp);
		this.isVoted = true;
		return temp;
	}

	/**
	 * Zählt den VoteValue um eins runter
	 *
	 * @return the new VoteValue
	 */
	public int downVote() {
		int temp = this.getVoteValue() - 1;
		this.setVoteValue(temp);
		this.isVoted = true;
		return temp;
	}
}

