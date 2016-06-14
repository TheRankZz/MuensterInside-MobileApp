package de.muensterinside.mobile.interfaces;

import java.util.List;

import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public interface VoteService {

	boolean upVote(int location_id, String deviceId);
	
	boolean downVote(int location_id, String deviceId);
	
	boolean isVoted(int location_id, String deviceId);
	
	List<Location> getMyVotes(String deviceId);
	
}
