package de.muensterinside.mobile;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.interfaces.LocationService;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;

public class MuensterInsideLocationImplMock implements LocationService {

    private List<Location> locationList;
    private Category category;

    public MuensterInsideLocationImplMock(){
        // create 6 test locations
        this.category = new Category("Essen");
        locationList = new ArrayList<>();
        locationList.add(new Location("Extrablatt","deviceId1",category));
        locationList.add(new Location("Vapiano","deviceId2",category));
        locationList.add(new Location("Pierhouse","deviceId3",category));
        locationList.add(new Location("Cafe Sieben","deviceId4",category));
        locationList.add(new Location("Extrablatt","deviceId1",category));
        locationList.add(new Location("Vapiano","deviceId2",category));
        locationList.add(new Location("Pierhouse","deviceId3",category));
        locationList.add(new Location("Cafe Sieben","deviceId4",category));
        locationList.add(new Location("Extrablatt","deviceId1",category));
        locationList.add(new Location("Vapiano","deviceId2",category));
        locationList.add(new Location("Pierhouse","deviceId3",category));
        locationList.add(new Location("Cafe Sieben","deviceId4",category));
        this.category = new Category("Party");
        locationList.add(new Location("Blaues Haus","deviceId5",category));
        locationList.add(new Location("Haifischbar","deviceId6",category));
        locationList.add(new Location("Blaues Haus","deviceId5",category));
        locationList.add(new Location("Haifischbar","deviceId6",category));
        locationList.add(new Location("Blaues Haus","deviceId5",category));
        locationList.add(new Location("Haifischbar","deviceId6",category));
        locationList.add(new Location("Blaues Haus","deviceId5",category));
        locationList.add(new Location("Haifischbar","deviceId6",category));
        this.category = new Category("Hotel");
        locationList.add(new Location("Hotel Conti","deviceId5",category));
        locationList.add(new Location("Example Hotel","deviceId6",category));
        locationList.add(new Location("Hotel Conti","deviceId5",category));
        locationList.add(new Location("Example Hotel","deviceId6",category));
        locationList.add(new Location("Hotel Conti","deviceId5",category));
        locationList.add(new Location("Example Hotel","deviceId6",category));
        locationList.add(new Location("Hotel Conti","deviceId5",category));
        locationList.add(new Location("Example Hotel","deviceId6",category));
        this.category = new Category("Shopping");
        locationList.add(new Location("H&M","deviceId5",category));
        locationList.add(new Location("New Yorker","deviceId6",category));
        locationList.add(new Location("H&M","deviceId5",category));
        locationList.add(new Location("New Yorker","deviceId6",category));
        locationList.add(new Location("H&M","deviceId5",category));
        locationList.add(new Location("New Yorker","deviceId6",category));
        locationList.add(new Location("H&M","deviceId5",category));
        locationList.add(new Location("New Yorker","deviceId6",category));
        this.category = new Category("Sehenswürdigkeiten");
        locationList.add(new Location("Schlossplatz","deviceId5",category));
        locationList.add(new Location("Domplatz","deviceId6",category));
        locationList.add(new Location("Schlossplatz","deviceId5",category));
        locationList.add(new Location("Domplatz","deviceId6",category));
        locationList.add(new Location("Schlossplatz","deviceId5",category));
        locationList.add(new Location("Domplatz","deviceId6",category));
        locationList.add(new Location("Schlossplatz","deviceId5",category));
        locationList.add(new Location("Domplatz","deviceId6",category));
        this.category = new Category("Veranstaltungen");
        locationList.add(new Location("I Love FH Party","deviceId5",category));
        locationList.add(new Location("Netflix & Chill Open Air","deviceId6",category));
        locationList.add(new Location("I Love FH Party","deviceId5",category));
        locationList.add(new Location("Netflix & Chill Open Air","deviceId6",category));
        locationList.add(new Location("I Love FH Party","deviceId5",category));
        locationList.add(new Location("Netflix & Chill Open Air","deviceId6",category));
        locationList.add(new Location("I Love FH Party","deviceId5",category));
        locationList.add(new Location("Netflix & Chill Open Air","deviceId6",category));
    }

    public Location getLocation(int loc_id){
        return locationList.get(loc_id);
    }

    public List<Location> getAllLocation(){
        return locationList;
    }

    public List<Location> getLocationByCategory(int cat_id){

        return locationList;
    }

    public List<Location> getMyLocation(String deviceId){

        return locationList;
    }

    public boolean addLocation(Location loc){
        //mocking: do nothing!
        return true;
    }

    public boolean removeLocation(int loc_id){
        //mocking: do nothing!
        return true;
    }
}
