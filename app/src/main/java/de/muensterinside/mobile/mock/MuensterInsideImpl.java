package de.muensterinside.mobile.mock;


import com.Wsdl2Code.WebServices.MobileWebserviceImplService.MobileWebserviceImplService;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.categoryTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.commentTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.deviceResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.deviceTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.returncodeResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.categoryListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.commentListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.imageResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.isVotedRepsonse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationResponse;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


import de.muensterinside.mobile.entities.*;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class MuensterInsideImpl implements MobileWebserviceImpl{

    private MobileWebserviceImplService webservice;
    private String myDeviceId;
    private int myIntDeviceId;
    private String myUsername;

    public MuensterInsideImpl() {
        this.webservice = new MobileWebserviceImplService();
    }

    @Override
    public List<Category> getCategories()throws Exception{
        categoryListResponse response = this.webservice.getCategories();
        if(response.returnCodeField != 0){
            throw new Exception("GetCategories fehlgeschlagen");
        }
        List<Category> result = new ArrayList<Category>();
        Enumeration<categoryTO> categoryEnum = response.categoryList.elements();
        while (categoryEnum.hasMoreElements()){
            categoryTO category = categoryEnum.nextElement();
            result.add(new Category(category.name));
        }
        return result;
    }

    @Override
    public Device register(String deviceId, String username)throws Exception{
        deviceResponse response = this.webservice.register(deviceId, username);
        if(response.returnCodeField != 0){
            throw new Exception("Registrierung fehlgeschlagen");
        }
        this.myDeviceId = deviceId;
        this.myUsername = username;
        return new Device(response.device.androidUuid, response.device.username);
    }

    @Override
    public Device login(String deviceId)throws Exception{
        deviceResponse response = this.webservice.login(deviceId);
        if(response.returnCodeField != 0){
            throw new Exception("Login fehlgeschlagen");
        }
        this.myDeviceId = deviceId;
        Device device = register(myDeviceId, myUsername);
        this.myIntDeviceId = device.hashCode();
        return device;
    }

    @Override
    public List<Location> getLocationsByCategory(int cat_id)throws Exception{
        locationListResponse response = this.webservice.getLocationsByCategory(cat_id);
        if(response.returnCodeField != 0){
            throw new Exception("getLocationsByCategory fehlgeschlagen");
        }
        List<Location> result = new ArrayList<Location>();
        List<Category> categories = getCategories();
        Category category = null;
        Device device = login(myDeviceId);
        for(int i=0; i<categories.size();i++){
            if(categories.get(i).getId() == cat_id){
                category = categories.get(i);
            }
        }
        Enumeration<locationTO> locationEnum = response.locationList.elements();
        while (locationEnum.hasMoreElements()){
            locationTO location = locationEnum.nextElement();
            result.add(new Location(location.name, location.description, location.link, device, category));

        }
        return result;
    }

    @Override
    public int saveLocation(String name, String description,
                            String link, int category_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.saveLocation(name, description, link, category_id, deviceId);
        int code;
        if(response.returnCodeField != 0){
            throw new Exception("Speichern der Location fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }

    @Override
    public List<Comment> getCommentsByLocation(int loc_id)throws Exception{
        commentListResponse response = this.webservice.getCommentsByLocation(loc_id);
        if(response.returnCodeField != 0){
            throw new Exception("GetCommentsByLocation fehlgeschlagen");
        }
        List<Comment> result = new ArrayList<Comment>();
        Location location = getLocation(loc_id);
        Device device = login(myDeviceId);
        Enumeration<commentTO> commentEnum = response.commentList.elements();
        while (commentEnum.hasMoreElements()){
            commentTO comment = commentEnum.nextElement();
            result.add(new Comment(comment.text, device, location));
        }
        return result;
    }


    public List<Comment> getMyComments(int deviceId)throws Exception{
        commentListResponse response = this.webservice.getMyComments(deviceId);
        if(response.returnCodeField != 0){
            throw new Exception("GetMyComments fehlgeschlagen");
        }
        List<Comment> result = new ArrayList<Comment>();
        Device device = new Device();
        if(deviceId == myIntDeviceId){
            device = login(myDeviceId);
        }
        Enumeration<commentTO> commentEnum = response.commentList.elements();
        while (commentEnum.hasMoreElements()){
            commentTO comment = commentEnum.nextElement();
            Comment com = new Comment();
            com.setText(comment.text);
            result.add(com);
        }
        return result;
    }


    public int saveComment(String text, int deviceId, int locationId)throws Exception{
        returncodeResponse response = this.webservice.saveComment(text, deviceId, locationId);
        int code;
        if(response.returnCodeField != 0){
            throw new Exception("Speichern des Kommentars fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public int deleteComment(int comment_id)throws Exception {
        returncodeResponse response = this.webservice.deleteComment(comment_id);
        int code;
        if(response.returnCodeField != 0){
            throw new Exception("Löschen des Kommentars fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public List<Location> getMyVotes(int deviceId)throws Exception{
        locationListResponse response = this.webservice.getMyVotes(deviceId);
        if(response.returnCodeField != 0){
            throw new Exception("GetMyVotes fehlgeschlagen");
        }
        List<Location> result = new ArrayList<Location>();
        Enumeration<locationTO> locationEnum = response.locationList.elements();
        while (locationEnum.hasMoreElements()){
            locationTO location = locationEnum.nextElement();
            Location loc = new Location();
            loc.setName(location.name);
            loc.setDescription(location.description);
            loc.setLink(location.link);
            loc.setVoteValue(location.votevalue);
            result.add(loc);
        }
        return result;

    }


    public int upVote(int location_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.upVote(location_id, deviceId);
        int code;
        if(response.returnCodeField != 0){
            throw new Exception("Upvote fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public int downVote(int location_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.downVote(location_id, deviceId);
        int code;
        if(response.returnCodeField != 0){
            throw new Exception("Downvote fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public boolean isVoted(int location_id, int deviceId)throws Exception{
        isVotedRepsonse response = this.webservice.isVoted(location_id, deviceId);
        if(response.returnCodeField != 0){
            throw new Exception("isVoted fehlgeschlagen");
        }
        else {
            return true;
        }
    }


    public int uploadImage(int location_id, String mimeType, String imageDataBase64)throws Exception{


        returncodeResponse response = this.webservice.uploadImage(location_id, mimeType, imageDataBase64);
        int code;
        if(response.returnCodeField != 0){
            throw new Exception("Bild hochladen fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public Image downloadImage(int location_id)throws Exception{
        imageResponse response = this.webservice.downloadImage(location_id);
        if(response.returnCodeField != 0){
            throw new Exception("Bild herunterladen fehlgeschlagen");
        }

        String encoded = response.imageDataBase64;
        byte[] decoded = encoded.getBytes();
        Image image = new Image();
        image.setContent(decoded);
        image.setMimeType(response.mimeType);
        return image;
    }


    public Location getLocation(int id)throws Exception{
        locationResponse response = this.webservice.getLocation(id);
        if(response.returnCodeField != 0){
            throw new Exception("getLocation fehlgeschlagen");
        }
        Location location = getLocation(id);
        return location;
    }


    public List<Location> getMyLocations(int deviceId)throws Exception{
        locationListResponse response = this.webservice.getMyLocations(deviceId);
        if(response.returnCodeField != 0){
            throw new Exception("GetMyLocations fehlgeschlagen");
        }
        List<Location> result = new ArrayList<Location>();
        Enumeration<locationTO> locationEnum = response.locationList.elements();
        while (locationEnum.hasMoreElements()){
            locationTO location = locationEnum.nextElement();
            Location loc = new Location();
            loc.setName(location.name);
            loc.setDescription(location.description);
            loc.setLink(location.link);
            loc.setVoteValue(location.votevalue);
            result.add(loc);
        }
        return result;
    }

}
