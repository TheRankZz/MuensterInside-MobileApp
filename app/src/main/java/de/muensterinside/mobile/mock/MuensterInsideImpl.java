package de.muensterinside.mobile.mock;


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
 * @author Julia Bracht, Nicolas Burchert
 */
public class MuensterInsideImpl implements MobileWebserviceImpl{

    private KMobileWebserviceImplService webservice;
    private String myDeviceId;
    private int myIntDeviceId;
    private String myUsername;

    public MuensterInsideImpl() {
        this.webservice = new KMobileWebserviceImplService();
    }

    /**
     * @return result,  Liste der Kategorien
     */
    @Override
    public List<Category> getCategories()throws Exception{
        categoryListResponse response = this.webservice.getCategories();
        List<Category> result = new ArrayList<Category>();
        if(response.returnCode != 0){
            result = null;
            throw new Exception(response.message);
        }
        else {
            Enumeration<categoryTO> categoryEnum = response.categoryList.elements();
            while (categoryEnum.hasMoreElements()) {
                categoryTO category = categoryEnum.nextElement();
                result.add(new Category(category.name));
            }
        }
        return result;
    }

    /**
     * @param deviceId, die Identifizitaet des Devices
     * @param username, der Name des Nutzers
     * @return dev, das Device Objekt
     */
    @Override
    public Device register(String deviceId, String username)throws Exception{
        deviceResponse response = this.webservice.register(deviceId, username);
        Device dev = new Device();
        if(response.returnCode != 0){
            dev = null;
            throw new Exception(response.message);
        }
        else {
            deviceTO device = response.device;
            this.myDeviceId = device.androidUuid;
            this.myUsername = device.username;

            dev.setAndroidUuid(device.androidUuid);
            dev.setId(device.id);
            dev.setUsername(device.username);
        }
        return dev;
    }

    /**
     * @param deviceId, die Identifizitaet des Devices
     * @return dev, das Device Objekt
     */
    @Override
    public Device login(String deviceId)throws Exception{
        deviceResponse response = this.webservice.login(deviceId);
        Device dev = new Device();
        if(response.returnCode != 0){
            dev = null;
            throw new Exception(response.message);
        }
        else {
            deviceTO device = response.device;
            this.myDeviceId = device.androidUuid;
            this.myUsername = device.username;
            this.myIntDeviceId = device.id;

            dev.setAndroidUuid(device.androidUuid);
            dev.setId(device.id);
            dev.setUsername(device.username);
        }
        return dev;
    }

    /**
     * @param cat_id, die Identifizitaet der Kategorie
     * @return result, eine Liste der Locations
     */
    @Override
    public List<Location> getLocationsByCategory(int cat_id)throws Exception{
        locationListResponse response = this.webservice.getLocationsByCategory(cat_id);
        List<Location> result = new ArrayList<Location>();
        if(response.returnCode != 0){
            result = null;
            throw new Exception(response.message);
        }
        else {
            Enumeration<locationTO> locationEnum = response.locationList.elements();
            while (locationEnum.hasMoreElements()) {
                locationTO location = locationEnum.nextElement();
                Location l = new Location();
                l.setId(location.id);
                l.setName(location.name);
                l.setLink(location.link);
                l.setDescription(location.description);
                l.setVoteValue(location.votevalue);
                result.add(l);
            }
        }
        return result;
    }

    /**
     * @param name, der Name der Location
     * @param description, die Beschreibung der Location
     * @param link, der Link der Location
     * @param category_id, die Identifizitaet der Kategorie
     * @param deviceId, die Identifizitaet des Devices
     * @return code, gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */
    @Override
    public int saveLocation(String name, String description,
                            String link, int category_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.saveLocation(name, description, link, category_id, deviceId);
        int code;
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            code = 0;
            return code;
        }
    }



    /**
     * @param loc_id, die Identifizitaet der Location
     * @return result, eine Liste der Kommentare
     */
    @Override
    public List<Comment> getCommentsByLocation(int loc_id)throws Exception{
        commentListResponse response = this.webservice.getCommentsByLocation(loc_id);
        List<Comment> result = new ArrayList<Comment>();
        if(response.returnCode != 0){
            result = null;
            throw new Exception(response.message);
        }
        else {
            Enumeration<commentTO> commentEnum = response.commentList.elements();
            while (commentEnum.hasMoreElements()) {
                commentTO comment = commentEnum.nextElement();
                Comment c = new Comment();
                c.setId(comment.id);
                c.setText(comment.text);
                c.setDate(comment.createdAt);
                result.add(c);
            }
        }
        return result;
    }


    /**
     * @param deviceId, die Identifizitaet des Devices
     * @return result, eine Liste der Kommentare
     */
    public List<Comment> getMyComments(int deviceId)throws Exception{
        commentListResponse response = this.webservice.getMyComments(deviceId);
        List<Comment> result = new ArrayList<Comment>();
        if(response.returnCode != 0){
            result = null;
            throw new Exception(response.message);
        }
        else {
            Enumeration<commentTO> commentEnum = response.commentList.elements();
            while (commentEnum.hasMoreElements()) {
                commentTO comment = commentEnum.nextElement();
                Comment com = new Comment();
                com.setId(comment.id);
                com.setText(comment.text);
                com.setDate(comment.createdAt);
                result.add(com);
            }
        }
        return result;
    }

    /**
     * @param text, der Text des Kommentars
     * @param deviceId, die Identifizitaet des Devices
     * @param locationId, die Identifizitaet der Location
     * @return code, gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */

    public int saveComment(String text, int deviceId, int locationId)throws Exception{
        returncodeResponse response = this.webservice.saveComment(text, deviceId, locationId);
        int code;
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            code = 0;
            return code;
        }
    }

    /**
     * @param comment_id, die Identifizaet des Kommentars
     * @return code, gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */
    public int deleteComment(int comment_id)throws Exception {
        returncodeResponse response = this.webservice.deleteComment(comment_id);
        int code;
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            code = 0;
            return code;
        }
    }

    /**
     * @param deviceId, die Identifizaet des Devices
     * @return result, eine Liste der Locations
     */
    public List<Location> getMyVotes(int deviceId)throws Exception{
        locationListResponse response = this.webservice.getMyVotes(deviceId);
        List<Location> result = new ArrayList<Location>();
        if(response.returnCode != 0){
            result = null;
            throw new Exception(response.message);
        }
        else {
            Enumeration<locationTO> locationEnum = response.locationList.elements();
            while (locationEnum.hasMoreElements()) {
                locationTO location = locationEnum.nextElement();
                Location loc = new Location();
                loc.setName(location.name);
                loc.setDescription(location.description);
                loc.setLink(location.link);
                loc.setVoteValue(location.votevalue);
                loc.setId(location.id);
                result.add(loc);
            }
        }
        return result;

    }


    /**
     * @param location_id, die Identifizitaet der Location
     * @param deviceId, die Identifizitaet des Devices
     * @return response.returnCode, gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */
    public int upVote(int location_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.upVote(location_id, deviceId);
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            return response.returnCode;
        }
    }


    /**
     * @param location_id, die Identifizitaet der Location
     * @param deviceId, die Identifizitaet des Devices
     * @return response.returnCode, gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */
    public int downVote(int location_id, int deviceId)throws Exception{
        returncodeResponse response = this.webservice.downVote(location_id, deviceId);
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            return response.returnCode;
        }
    }

    /**
     * @param location_id, die Identifizitaet der Location
     * @param deviceId, die Identifizitaet des Devices
     * @return response.isVoted , gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */

    public boolean isVoted(int location_id, int deviceId)throws Exception{
        isVotedResponse response = this.webservice.isVoted(location_id, deviceId);
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            return response.isVoted;
        }
    }


    /**
     * @param location_id, die Identifizitaet der Location
     * @param mimeType, der Typ des Bildes
     * @param imageDataBase64, das Bild im  Base64  String
     * @return code, gibt an, ob Methode erfolgreich war (0 = erfolgreich)
     */
    public int uploadImage(int location_id, String mimeType, String imageDataBase64)throws Exception{
        returncodeResponse response = this.webservice.uploadImage(location_id, mimeType, imageDataBase64);
        int code;
        if(response.returnCode != 0){
            throw new Exception(response.message);
        }
        else {
            code = 0;
            return code;
        }
    }


    /**
     * @param location_id, die Identifizitaet der Location
     * @return image, gibt das Bild zurueck
     */
    public Image downloadImage(int location_id)throws Exception{
        imageResponse response = this.webservice.downloadImage(location_id);
        Image image = new Image();
        if(response.returnCode != 0){
            image = null;
            throw new Exception(response.message);
        }
        else {
            String encoded = response.imageDataBase64;
            byte[] decoded = encoded.getBytes();
            image.setContent(decoded);
            image.setMimeType(response.mimeType);
        }
        return image;
    }

    /**
     * @param loc_id, die Identifizitaet der Location
     * @return l, gibt das Location Objekt zurueck
     */
    public Location getLocation(int loc_id)throws Exception{
        locationResponse response = this.webservice.getLocation(loc_id);
        Location l = new Location();
        if(response.returnCode != 0){
            l = null;
            throw new Exception(response.message);
        }
        else {
            locationTO location = response.location;
            l.setId(location.id);
            l.setName(location.name);
            l.setDescription(location.description);
            l.setLink(location.link);
            l.setVoteValue(location.votevalue);
        }
        return l;
    }

    /**
     * @param deviceId, die Identifizitaet des Devices
     * @return result, gibt eine Liste der Locations zurueck
     */
    public List<Location> getMyLocations(int deviceId)throws Exception{
        locationListResponse response = this.webservice.getMyLocations(deviceId);
        List<Location> result = new ArrayList<Location>();
        Enumeration<locationTO> locationEnum = response.locationList.elements();
        if(response.returnCode != 0){
            result = null;
            throw new Exception(response.message);
        }
        else {
            while (locationEnum.hasMoreElements()) {
                locationTO location = locationEnum.nextElement();
                Location loc = new Location();
                loc.setName(location.name);
                loc.setDescription(location.description);
                loc.setLink(location.link);
                loc.setVoteValue(location.votevalue);
                loc.setId((location.id));
                result.add(loc);
            }
        }
        return result;
    }

}
