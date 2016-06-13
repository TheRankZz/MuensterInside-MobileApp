package de.muensterinside.mobile.mock;


import com.Wsdl2Code.WebServices.MobileWebserviceImplService.VectorcategoryTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.categoryTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.commentTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.deviceTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationTO;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.deviceResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.returncodeResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.categoryListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.commentListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.imageResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.isVotedResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationListResponse;
import com.Wsdl2Code.WebServices.MobileWebserviceImplService.locationResponse;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


import Soap.KMobileWebserviceImplService;
import de.muensterinside.mobile.entities.*;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class MuensterInsideImpl implements MobileWebserviceImpl{

    private KMobileWebserviceImplService webservice;
    private String myDeviceId;
    private int myIntDeviceId;
    private String myUsername;

    public MuensterInsideImpl() {
        this.webservice = new KMobileWebserviceImplService();
    }

    @Override
    public List<Category> getCategories()throws Exception{
        categoryListResponse response = this.webservice.getCategories();
        if(response.returnCode != 0){
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
        if(response.returnCode != 0){
            throw new Exception("Registrierung fehlgeschlagen!");
        }
        deviceTO device = response.device;
        this.myDeviceId = device.androidUuid;
        this.myUsername = device.username;

        Device dev = new Device();
        dev.setAndroidUuid(device.androidUuid);
        dev.setId(device.id);
        dev.setUsername(device.username);
        return dev;
    }

    @Override
    public Device login(String deviceId)throws Exception{
        deviceResponse response = this.webservice.login(deviceId);

        if(response.returnCode != 0){
            throw new Exception("Login fehlgeschlagen");
        }
        deviceTO device = response.device;
        this.myDeviceId = device.androidUuid;
        this.myUsername = device.username;
        this.myIntDeviceId = device.id;


        Device dev = new Device();
        dev.setAndroidUuid(device.androidUuid);
        dev.setId(device.id);
        dev.setUsername(device.username);
        return dev;
    }

    @Override
    public List<Location> getLocationsByCategory(int cat_id)throws Exception{
        locationListResponse response = this.webservice.getLocationsByCategory(cat_id);
        if(response.returnCode != 0){
            throw new Exception("getLocationsByCategory fehlgeschlagen");
        }
        List<Location> result = new ArrayList<Location>();
        Enumeration<locationTO> locationEnum = response.locationList.elements();
        while (locationEnum.hasMoreElements()){
            locationTO location = locationEnum.nextElement();
            Location l = new Location();
            l.setId(location.id);
            l.setName(location.name);
            l.setLink(location.link);
            l.setDescription(location.description);
            l.setVoteValue(location.votevalue);
            result.add(l);
        }
        return result;
    }

    @Override
    public int saveLocation(String name, String description,
                            String link, int category_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.saveLocation(name, description, link, category_id, deviceId);
        int code;
        if(response.returnCode != 0){
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
        if(response.returnCode == 20){
            return null;
        }
        if(response.returnCode != 0){
            throw new Exception("GetCommentsByLocation fehlgeschlagen");
        }
        List<Comment> result = new ArrayList<Comment>();
        Enumeration<commentTO> commentEnum = response.commentList.elements();
        while (commentEnum.hasMoreElements()){
            commentTO comment = commentEnum.nextElement();
            Comment c = new Comment();
            c.setText(comment.text);
            result.add(c);
        }
        return result;
    }


    public List<Comment> getMyComments(int deviceId)throws Exception{
        commentListResponse response = this.webservice.getMyComments(deviceId);
        if(response.returnCode != 0){
            throw new Exception("GetMyComments fehlgeschlagen");
        }
        if(response.returnCode == 20){
            return null;
        }
        List<Comment> result = new ArrayList<Comment>();
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
        if(response.returnCode != 0){
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
        if(response.returnCode != 0){
            throw new Exception("Löschen des Kommentars fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public List<Location> getMyVotes(int deviceId)throws Exception{
        locationListResponse response = this.webservice.getMyVotes(deviceId);
        if(response.returnCode != 0){
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
        if(response.returnCode != 0){
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
        if(response.returnCode != 0){
            throw new Exception("Downvote fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public boolean isVoted(int location_id, int deviceId)throws Exception{
        isVotedResponse response = this.webservice.isVoted(location_id, deviceId);
        if(response.returnCode != 0){
            throw new Exception("isVoted fehlgeschlagen");
        }
        else {
            return response.isVoted;
        }
    }


    public int uploadImage(int location_id, String mimeType, String imageDataBase64)throws Exception{


        returncodeResponse response = this.webservice.uploadImage(location_id, mimeType, imageDataBase64);
        int code;
        if(response.returnCode != 0){
            throw new Exception("Bild hochladen fehlgeschlagen");
        }
        else {
            code = 0;
            return code;
        }
    }


    public Image downloadImage(int location_id)throws Exception{
        imageResponse response = this.webservice.downloadImage(location_id);
        if(response.returnCode != 0){
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
        if(response.returnCode != 0){
            throw new Exception("getLocation fehlgeschlagen");
        }
        locationTO location = response.location;
        Location l = new Location();
        l.setId(location.id);
        l.setName(location.name);
        l.setDescription(location.description);
        l.setDescription(location.link);
        return l;
    }


    public List<Location> getMyLocations(int deviceId)throws Exception{
        locationListResponse response = this.webservice.getMyLocations(deviceId);
        if(response.returnCode != 0){
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
