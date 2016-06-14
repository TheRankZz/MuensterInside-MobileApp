package de.muensterinside.mobile.interfaces;

import java.util.List;

import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public interface LocationService {
	
	Location getLocation(int loc_id);
	
	List<Location> getAllLocation();
	
	List<Location> getLocationByCategory(int cat_id);
	
	List<Location> getMyLocation(String deviceId);
	
	boolean addLocation(Location loc);
	
	boolean removeLocation(int loc_id);

}
