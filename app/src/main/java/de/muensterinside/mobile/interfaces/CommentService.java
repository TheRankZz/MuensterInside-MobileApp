package de.muensterinside.mobile.interfaces;

import java.util.List;
import de.muensterinside.mobile.modals.Comment;

public interface CommentService {

	Comment getComment(int com_id);
	
	List<Comment> getCommentsByLocation(int loc_id);
	
	List<Comment> getLastCommentByLocation(int loc_id);
	
	List<Comment> getMyComments(String deviceId);
	
	boolean addComment(Comment com);
	
	boolean removeComment(int com_id);
	
}
