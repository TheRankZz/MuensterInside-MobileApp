package de.muensterinside.interfaces;

import java.util.List;
import de.muensterinside.modals.Location;

public interface VoteService {

	public boolean upVote(int location_id, String deviceId);
	
	public boolean downVote(int location_id, String deviceId);
	
	public boolean isVoted(int location_id, String deviceId);
	
	public List<Location> getMyVotes(String deviceId);
	
}
