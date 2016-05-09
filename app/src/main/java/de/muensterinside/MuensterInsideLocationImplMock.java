package de.muensterinside;

import java.util.ArrayList;
import java.util.List;
import android.app.Application;
import de.muensterinside.interfaces.LocationService;
import de.muensterinside.modals.Category;
import de.muensterinside.modals.Location;

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
        locationList.add(new Location("Burgercult","deviceId5",category));
        locationList.add(new Location("Hans im Glück","deviceId6",category));
    }

    public Location getLocation(int loc_id){
        return locationList.get(loc_id);
    }

    public List<Location> getAllLocation(){
        return locationList;
    }

    public List<Location> getLocationByCategory(int cat_id){
        //mocking: do nothing!
        return locationList;
    }

    public List<Location> getMyLocation(String deviceId){
        //mocking: do nothing!
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
