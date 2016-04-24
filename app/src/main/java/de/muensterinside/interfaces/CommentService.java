package de.muensterinside.interfaces;

import java.util.List;
import de.muensterinside.modals.Comment;

public interface CommentService {

	public Comment getComment(int com_id);
	
	public List<Comment> getCommentsByLocation(int loc_id);
	
	public List<Comment> getLastCommentByLocation(int loc_id);
	
	public List<Comment> getMyComments(String deviceId);
	
	public boolean addComment(Comment com);
	
	public boolean removeComment(int com_id);
	
}
