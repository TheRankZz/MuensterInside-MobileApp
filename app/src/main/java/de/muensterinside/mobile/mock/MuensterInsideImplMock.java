package de.muensterinside.mobile.mock;



import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.muensterinside.mobile.entities.*;


/**
 * Created By Julia Bracht and Nicolas Burchert
 * Diese Klasse ersetzt die Verbindung zum Server als Mock-Objekt.
 * Die Methoden sind aufrufbar, liefern aber nur starre Testdaten.
 */
public class MuensterInsideImplMock implements MobileWebserviceImpl {

    private List<Location> locationList;
    private List<Category> categoryList;
    private List<Comment> commentList;
    private List<Vote> voteList;
    private Device device = new Device();
    private String deviceId;

    /**
     * Konstruktor
     * erstellt 6 Kategorien
     * erstellt 14 Locations
     */
    public MuensterInsideImplMock() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Essen"));
        categoryList.add(new Category("Party"));
        categoryList.add(new Category("Hotel"));
        categoryList.add(new Category("Shopping"));
        categoryList.add(new Category("Sehenswürdigkeiten"));
        categoryList.add(new Category("Veranstaltungen"));

        commentList = new ArrayList<>();
        locationList = new ArrayList<>();

        locationList.add(new Location("Extrablatt", "deviceId1", "link", device, categoryList.get(0)));
        locationList.get(0).setDescription("Man kann hier gut essen.");
        locationList.get(0).setVoteValue(10);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(0)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(0)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(0)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(0)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(0)));

        locationList.add(new Location("Vapiano", "deviceId2", "link", device, categoryList.get(0)));
        locationList.get(1).setDescription("Gute Auswahl.");
        locationList.get(1).setVoteValue(15);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(1)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(1)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(1)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(1)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(1)));

        locationList.add(new Location("Pierhouse", "deviceId3", "link", device, categoryList.get(0)));
        locationList.get(2).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(2).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(2)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(2)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(2)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(2)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(2)));

        locationList.add(new Location("Cafe Sieben", "deviceId4", "link", device, categoryList.get(0)));
        locationList.get(3).setDescription("Sehr leckere Chicken-Wings.");
        locationList.get(3).setVoteValue(11);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(3)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(3)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(3)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(3)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(3)));

        locationList.add(new Location("Blaues Haus", "deviceId5", "link", device, categoryList.get(1)));
        locationList.get(4).setDescription("Der Long Island ist der beste.");
        locationList.get(4).setVoteValue(35);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(4)));

        locationList.add(new Location("Haifischbar", "deviceId6", "link", device, categoryList.get(1)));
        locationList.get(5).setDescription("Dort kann man Sky gucken.");
        locationList.get(5).setVoteValue(40);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(5)));

        locationList.add(new Location("Hotel Conti", "deviceId5", "link", device, categoryList.get(2)));
        locationList.get(6).setDescription("Der Service ist klasse.");
        locationList.get(6).setVoteValue(9);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(6)));

        locationList.add(new Location("Example Hotel", "deviceId6", "link", device, categoryList.get(2)));
        locationList.get(7).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(7).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(7)));

        locationList.add(new Location("H&M", "deviceId5", "link", device, categoryList.get(3)));
        locationList.get(8).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(8).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(8)));

        locationList.add(new Location("New Yorker", "deviceId6", "link", device, categoryList.get(3)));
        locationList.get(9).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(9).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(9)));

        locationList.add(new Location("Schlossplatz", "deviceId5", "link", device, categoryList.get(4)));
        locationList.get(10).setDescription("Wunderschön.");
        locationList.get(10).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(10)));

        locationList.add(new Location("Domplatz", "deviceId6", "link", device, categoryList.get(4)));
        locationList.get(11).setDescription("Tolle Architektur.");
        locationList.get(11).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(11)));

        locationList.add(new Location("I Love FH Party", "deviceId5", "link", device, categoryList.get(5)));
        locationList.get(12).setDescription("Super Stimmung.");
        locationList.get(12).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(12)));

        locationList.add(new Location("Netflix & Chill Open Air", "deviceId6", "link", device, categoryList.get(5)));
        locationList.get(13).setDescription("Angenehm.");
        locationList.get(13).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(13)));

    }

    public List<Category> getCategories(){
        return categoryList;
    }


    public Device register(String deviceId, String username){
        this.device.setAndroidUuid(deviceId);
        this.device.setUsername(username);
        this.deviceId = deviceId;
        return this.device;
    }


    public Device login(String deviceId)throws Exception{
        if(this.device.getAndroidUuid()==deviceId){
            return this.device;
        }
        else {
            throw new Exception("Device nicht gefunden");
        }
    }


    public List<Location> getLocationsByCategory(int cat_id){
        List<Location> result = new ArrayList<Location>();
        for(int i=0; i<this.locationList.size(); i++){
            Location location = this.locationList.get(i);
            if(location.getCategory().getId() == cat_id){
                result.add(location);
            }
        }
        return result;
    }


    public int saveLocation(String name, String description,
                            String link, int category_id, int deviceId){
        int oldLength = this.locationList.size();
        Location location = new Location();
        location.setName(name);
        location.setDescription(description);
        location.setLink(link);
        location.setCategory(this.categoryList.get(category_id));
        location.setDevice(this.device);
        this.locationList.add(location);
        int newLength = this.locationList.size();
        if(oldLength != newLength){
            return 0;
        }
        else{
            return 1;
        }
    }

    public List<Comment> getCommentsByLocation(int loc_id){
        List<Comment> result = new ArrayList<Comment>();
        for(int i=0; i<this.commentList.size();i++){
            Comment comment = this.commentList.get(i);
            if(comment.getLocation().equals(this.locationList.get(loc_id))){
                result.add(comment);
            }
        }
        return result;
    }


    public List<Comment> getMyComments(int deviceId){
        List<Comment> result = new ArrayList<Comment>();
        for(int i=0; i<this.commentList.size();i++){
            Comment comment = this.commentList.get(i);
            if(comment.getDevice().equals(this.device)){
                result.add(comment);
            }
        }
        return result;
    }


    public int saveComment(String text, int deviceId, int locationId){
        int oldLength = this.commentList.size();
        Comment comment = new Comment();
        comment.setText(text);
        comment.setDevice(this.device);
        comment.setLocation(this.locationList.get(locationId));
        this.commentList.add(comment);
        int newLength = this.commentList.size();
        if(oldLength != newLength){
            return 0;
        }
        else{
            return 1;
        }
    }


    public int deleteComment(int comment_id){
        int oldLength = this.commentList.size();
        this.commentList.remove(comment_id);
        int newLength = this.commentList.size();
        if(oldLength != newLength){
            return 0;
        }
        else{
            return 1;
        }
    }


    public List<Location> getMyVotes(int deviceId){
        List<Location> result = new ArrayList<Location>();
        for(int i=0; i<this.locationList.size();i++){
            Location location = this.locationList.get(i);
            if(location.getDevice().equals(this.device)){
                result.add(location);
            }
        }
        return result;
    }


    public int upVote(int location_id, int deviceId){
        Location location = this.locationList.get(location_id);
        int oldVoteValue = location.getVoteValue();
        location.upVote();
        int newVoteValue = location.getVoteValue();
        if(oldVoteValue != newVoteValue){
            return 0;
        }
        else{
            return 1;
        }
    }


    public int downVote(int location_id, int deviceId){
        Location location = this.locationList.get(location_id);
        int oldVoteValue = location.getVoteValue();
        location.downVote();
        int newVoteValue = location.getVoteValue();
        if(oldVoteValue != newVoteValue){
            return 0;
        }
        else{
            return 1;
        }
    }


    public boolean isVoted(int location_id, int deviceId){
        Location location = this.locationList.get(location_id);
        boolean result = location.isVoted();
        return result;
    }


    public int uploadImage(int location_id, String mimeType, String imageDataBase64){
        // mocking: do nothing
        return 1;
    }


    public Image downloadImage(int location_id){
        // mocking: do nothing
        Image image = null;
        return image;
    }


    public Location getLocation(int id){
        return this.locationList.get(id);
    }


    public List<Location> getMyLocations(int deviceId){
        List<Location> result = new ArrayList<Location>();
        for(int i=0; i<this.locationList.size();i++){
            Location location = this.locationList.get(i);
            if(location.getDevice().equals(this.device)){
                result.add(location);
            }
        }
        return result;
    }



}
