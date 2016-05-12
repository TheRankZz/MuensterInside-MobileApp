package de.muensterinside.mobile.mock;

import android.bluetooth.BluetoothClass.Device;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.*;


/**
 * Created By Julia Bracht and Nicolas Burchert
 * Diese Klasse ersetzt die Verbindung zum Server als Mock-Objekt.
 * Die Methoden sind aufrufbar, liefern aber nur starre Testdaten.
 */
public class MuensterInsideImplMock implements MuensterInsideMobile {

    private List<Location> locationList;
    private List<Category> categoryList;
    private List<Comment> commentList;
    private List<Vote> voteList;

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
        locationList = new ArrayList<>();
        locationList.add(new Location("Extrablatt", "deviceId1", categoryList.get(0)));
        locationList.get(0).setDescription("Man kann hier gut essen.");
        locationList.get(0).setVoteValue(10);
        locationList.add(new Location("Vapiano", "deviceId2", categoryList.get(0)));
        locationList.get(1).setDescription("Gute Auswahl.");
        locationList.get(1).setVoteValue(15);
        locationList.add(new Location("Pierhouse", "deviceId3", categoryList.get(0)));
        locationList.get(2).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(2).setVoteValue(20);
        locationList.add(new Location("Cafe Sieben", "deviceId4", categoryList.get(0)));
        locationList.get(3).setDescription("Sehr leckere Chicken-Wings.");
        locationList.get(3).setVoteValue(11);
        locationList.add(new Location("Blaues Haus", "deviceId5", categoryList.get(1)));
        locationList.get(4).setDescription("Der Long Island ist der beste.");
        locationList.get(4).setVoteValue(35);
        locationList.add(new Location("Haifischbar", "deviceId6", categoryList.get(1)));
        locationList.get(5).setDescription("Dort kann man Sky gucken.");
        locationList.get(5).setVoteValue(40);
        locationList.add(new Location("Hotel Conti", "deviceId5", categoryList.get(2)));
        locationList.get(6).setDescription("Der Service ist klasse.");
        locationList.get(6).setVoteValue(9);
        locationList.add(new Location("Example Hotel", "deviceId6", categoryList.get(2)));
        locationList.get(7).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(7).setVoteValue(20);
        locationList.add(new Location("H&M", "deviceId5", categoryList.get(3)));
        locationList.get(8).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(8).setVoteValue(20);
        locationList.add(new Location("New Yorker", "deviceId6", categoryList.get(3)));
        locationList.get(9).setDescription("Gutes Preis/Leistungsverhältnis.");
        locationList.get(9).setVoteValue(20);
        locationList.add(new Location("Schlossplatz", "deviceId5", categoryList.get(4)));
        locationList.get(10).setDescription("Wunderschön.");
        locationList.get(10).setVoteValue(20);
        locationList.add(new Location("Domplatz", "deviceId6", categoryList.get(4)));
        locationList.get(11).setDescription("Tolle Architektur.");
        locationList.get(11).setVoteValue(20);
        locationList.add(new Location("I Love FH Party", "deviceId5", categoryList.get(5)));
        locationList.get(12).setDescription("Super Stimmung.");
        locationList.get(12).setVoteValue(20);
        locationList.add(new Location("Netflix & Chill Open Air", "deviceId6", categoryList.get(5)));
        locationList.get(13).setDescription("Angenehm.");
        locationList.get(13).setVoteValue(20);

    }

    public int register(String deviceId, String username){
        int i = 0;
        return i;
    }

    public Device login(String deviceId){
        Device d = null;
        return d;
    }

    public List<Category> getCategories(){
        return categoryList;
    }

    public boolean addCategory(Category cat){
        return true;
    }

    public boolean removeCategory(int cat_id){
        return true;
    }

    public Location getLocation(int loc_id){
        return locationList.get(loc_id);
    }

    public List<Location> getLocationsByCategory(int cat_id){
        return locationList;
    }

    public List<Location> getMyLocations(String deviceId){
        return locationList;
    }

    public boolean addLocation(Location loc){
        return true;
    }

    public boolean removeLocation(int loc_id){
        return true;
    }

    public int saveLocation(String name, String description, String link, String deviceId){
        int i = 0;
        return i;
    }

    public List<Comment> getCommentsByLocation(int loc_id){
        return commentList;
    }

    public List<Comment> getLastCommentsByLocation(int loc_id){
        return commentList;
    }

    public List<Comment> getMyComments(String deviceId){
        return commentList;
    }

    public int saveComment(String text, String deviceId){
        int i = 0;
        return i;
    }

    public int deleteComment(int comment_id){
        int i = 0;
        return i;
    }

    public List<Vote> getMyVotes(String deviceId){
        return voteList;
    }

    public int upVote(int location_id, String deviceId){
        int i = 0;
        return i;
    }

    public int downVote(int location_id, String deviceId){
        int i = 0;
        return i;
    }

}
