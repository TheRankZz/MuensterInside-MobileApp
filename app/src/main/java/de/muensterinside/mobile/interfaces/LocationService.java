package de.muensterinside.mobile.interfaces;

import java.util.List;
import de.muensterinside.mobile.modals.Location;

public interface LocationService {
	
	Location getLocation(int loc_id);
	
	List<Location> getAllLocation();
	
	List<Location> getLocationByCategory(int cat_id);
	
	List<Location> getMyLocation(String deviceId);
	
	boolean addLocation(Location loc);
	
	boolean removeLocation(int loc_id);

}
