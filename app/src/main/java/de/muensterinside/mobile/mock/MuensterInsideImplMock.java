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
    private Device device;
    private String deviceId;
    private String username;

    /**
     * Konstruktor
     * erstellt 6 Kategorien
     * erstellt 14 Locations
     */
    public MuensterInsideImplMock() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Essen"));
        categoryList.get(0).setId(0);
        categoryList.add(new Category("Party"));
        categoryList.get(1).setId(1);
        categoryList.add(new Category("Hotel"));
        categoryList.get(2).setId(2);
        categoryList.add(new Category("Shopping"));
        categoryList.get(3).setId(3);
        categoryList.add(new Category("Sehenswürdigkeiten"));
        categoryList.get(4).setId(4);
        categoryList.add(new Category("Veranstaltungen"));
        categoryList.get(5).setId(5);

        commentList = new ArrayList<>();
        locationList = new ArrayList<>();
        device = new Device();

        locationList.add(new Location(0,"Extrablatt", "deviceId1", "link", device, categoryList.get(0)));
        locationList.get(0).setDescription("Man kann hier gut essen.");
        locationList.get(0).setVoteValue(10);
        commentList.add(new Comment("Extrablatt ist toll", device, locationList.get(0)));
        commentList.add(new Comment("Extrablatt enstpricht meinen Erwartungen.", device, locationList.get(0)));
        commentList.add(new Comment("Bester Laden in Münster.", device, locationList.get(0)));
        commentList.add(new Comment("Extrablatt4theWin", device, locationList.get(0)));
        commentList.add(new Comment("genau", device, locationList.get(0)));

        locationList.add(new Location(1,"Vapiano", "deviceId2", "link", device, categoryList.get(0)));
        locationList.get(1).setDescription("Gute Auswahl.");
        locationList.get(1).setVoteValue(15);
        commentList.add(new Comment("Vapiano mag ich.", device, locationList.get(1)));
        commentList.add(new Comment("Die Pizza ist gut.", device, locationList.get(1)));
        commentList.add(new Comment("Der Salat ist der beste.", device, locationList.get(1)));
        commentList.add(new Comment("Nico mag das nicht so, ich finds gut.", device, locationList.get(1)));
        commentList.add(new Comment("Vapiano ist nicht mein Fall.", device, locationList.get(1)));

        locationList.add(new Location(2,"Pierhouse", "deviceId3", "link", device, categoryList.get(0)));
        locationList.get(2).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(2).setVoteValue(20);
        commentList.add(new Comment("Etwas teuer.", device, locationList.get(2)));
        commentList.add(new Comment("Mittagstisch ist gut.", device, locationList.get(2)));
        commentList.add(new Comment("Hafen halt.", device, locationList.get(2)));
        commentList.add(new Comment("Kann man mal machen.", device, locationList.get(2)));
        commentList.add(new Comment("ChickenWings sind dort die besten.", device, locationList.get(2)));

        locationList.add(new Location(3,"Cafe Sieben", "deviceId4", "link", device, categoryList.get(0)));
        locationList.get(3).setDescription("Sehr leckere Chicken-Wings.");
        locationList.get(3).setVoteValue(11);
        commentList.add(new Comment("Hafen halt.", device, locationList.get(3)));
        commentList.add(new Comment("Beste Location.", device, locationList.get(3)));
        commentList.add(new Comment("Ich mag es.", device, locationList.get(3)));
        commentList.add(new Comment("Qualitativ hochwertig.", device, locationList.get(3)));
        commentList.add(new Comment("Mittags sehr angenehm.", device, locationList.get(3)));

        locationList.add(new Location(4,"Blaues Haus", "deviceId5", "link", device, categoryList.get(1)));
        locationList.get(4).setDescription("Der Long Island ist der beste.");
        locationList.get(4).setVoteValue(35);
        commentList.add(new Comment("Blaues Haus ist urig.", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(4)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(4)));

        locationList.add(new Location(5,"Haifischbar", "deviceId6", "link", device, categoryList.get(1)));
        locationList.get(5).setDescription("Dort kann man Sky gucken.");
        locationList.get(5).setVoteValue(40);
        commentList.add(new Comment("Hier hat Bayern gewonnen.", device, locationList.get(5)));
        commentList.add(new Comment("Nur der FCB.", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(5)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(5)));

        locationList.add(new Location(6,"Hotel Conti", "deviceId5", "link", device, categoryList.get(2)));
        locationList.get(6).setDescription("Der Service ist klasse.");
        locationList.get(6).setVoteValue(9);
        commentList.add(new Comment("Die Lage am  Bahnhof spricht für sich.", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(6)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(6)));

        locationList.add(new Location(7,"Example Hotel", "deviceId6", "link", device, categoryList.get(2)));
        locationList.get(7).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(7).setVoteValue(20);
        commentList.add(new Comment("Kommentar 1", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(7)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(7)));

        locationList.add(new Location(8,"H&M", "deviceId5", "link", device, categoryList.get(3)));
        locationList.get(8).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(8).setVoteValue(20);
        commentList.add(new Comment("Kinderarbeit.", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(8)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(8)));

        locationList.add(new Location(9,"New Yorker", "deviceId6", "link", device, categoryList.get(3)));
        locationList.get(9).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(9).setVoteValue(20);
        commentList.add(new Comment("Naja, qualitativ doof.", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(9)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(9)));

        locationList.add(new Location(10,"Schlossplatz", "deviceId5", "link", device, categoryList.get(4)));
        locationList.get(10).setDescription("Wunderschön.");
        locationList.get(10).setVoteValue(20);
        commentList.add(new Comment("Super einfach, einfach super.", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(10)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(10)));

        locationList.add(new Location(11,"Domplatz", "deviceId6", "link", device, categoryList.get(4)));
        locationList.get(11).setDescription("Tolle Architektur.");
        locationList.get(11).setVoteValue(20);
        commentList.add(new Comment("Super Location, netter Ort.", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(11)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(11)));

        locationList.add(new Location(12,"I Love FH Party", "deviceId5", "link", device, categoryList.get(5)));
        locationList.get(12).setDescription("Super Stimmung.");
        locationList.get(12).setVoteValue(20);
        commentList.add(new Comment("Fh Partys sind die besten.", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(12)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(12)));

        locationList.add(new Location(13,"Netflix & Chill Open Air", "deviceId6", "link", device, categoryList.get(5)));
        locationList.get(13).setDescription("Angenehm.");
        locationList.get(13).setVoteValue(20);
        commentList.add(new Comment("Haha ernsthaft?", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 2", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 3", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 4", device, locationList.get(13)));
        commentList.add(new Comment("Kommentar 5", device, locationList.get(13)));

    }

    public List<Category> getCategories(){
        return categoryList;
    }


    public Device register(String deviceId, String username){
        Device device = new Device();
        device.setAndroidUuid(deviceId);
        device.setUsername(username);
        device.setId(0);
        this.deviceId = deviceId;
        this.username = username;
        this.device = device;
        return device;
    }


    public Device login(String deviceId)throws Exception{
        if(this.device.getAndroidUuid()==deviceId){
            return this.device;
        }
        else {
            return null;
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
        location.setId(oldLength++);
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
            if(comment.getDevice().getId() == deviceId){
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
