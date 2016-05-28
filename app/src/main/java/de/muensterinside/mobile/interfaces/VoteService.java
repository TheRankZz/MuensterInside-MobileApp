package de.muensterinside.mobile.interfaces;

import java.util.List;

import de.muensterinside.mobile.entities.Location;

public interface VoteService {

	boolean upVote(int location_id, String deviceId);
	
	boolean downVote(int location_id, String deviceId);
	
	boolean isVoted(int location_id, String deviceId);
	
	List<Location> getMyVotes(String deviceId);
	
}
