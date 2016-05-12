package de.muensterinside.mobile.mock;

import java.util.List;
import android.bluetooth.BluetoothClass.Device;


import de.muensterinside.mobile.entities.*;


/**
 * Interface: Webservice für Mobile-Clients
 * @author Lennart Giesen, Julius Wessing
 *
 */
public interface MuensterInsideMobile {
	
	public int register(String deviceId, String username);

	public Device login(String deviceId);
	
	
	public List<Category> getCategories();
	
	
	public List<Location> getLocationsByCategory(int cat_id);

	public Location getLocation(int id);
	
	public List<Location> getMyLocations(String deviceId);
	
	public int saveLocation(String name, String description, 
			String link, String deviceId);

	
	public List<Comment> getCommentsByLocation(int loc_id);
	
	public List<Comment> getLastCommentsByLocation(int loc_id);

	public List<Comment> getMyComments(String deviceId);
	
	public int saveComment(String text, String deviceId);
	
	public int deleteComment(int comment_id);
	

	public List<Vote> getMyVotes(String deviceId);
	
	public int upVote(int location_id, String deviceId);
	
	public int downVote(int location_id, String deviceId);	
	
}