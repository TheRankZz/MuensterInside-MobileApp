package de.muensterinside.mobile.mock;

import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Image;
import de.muensterinside.mobile.entities.Location;

/**
 * 
 * @author Julia Bracht, Nicolas Burchert, Lennart Giesen, Julius Wessing
 *
 */

public interface MobileWebserviceImpl {
	
	
	public List<Category> getCategories()throws Exception;


	public Device register(String deviceId, String username)throws Exception;


	public Device login(String deviceId)throws Exception;


	public List<Location> getLocationsByCategory(int cat_id)throws Exception;


	public int saveLocation(String name, String description,
			String link, int category_id, int deviceId)throws Exception;

	public List<Comment> getCommentsByLocation(int loc_id)throws Exception;


	public List<Comment> getMyComments(int deviceId)throws Exception;


	public int saveComment(String text, int deviceId, int locationId)throws Exception;


	public int deleteComment(int comment_id)throws Exception;


	public List<Location> getMyVotes(int deviceId)throws Exception;


	public int upVote(int location_id, int deviceId)throws Exception;


	public int downVote(int location_id, int deviceId)throws Exception;


	public boolean isVoted(int location_id, int deviceId)throws Exception;


	public int uploadImage(int location_id, String mimeType, String imageDataBase64)throws Exception;


	public Image downloadImage(int location_id)throws Exception;


	public Location getLocation(int id)throws Exception;


	public List<Location> getMyLocations(int deviceId)throws Exception;

}
