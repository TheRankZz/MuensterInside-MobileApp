package de.muensterinside.interfaces;

import java.util.List;
import de.muensterinside.modals.Location;

public interface LocationService {
	
	public Location getLocation(int loc_id);
	
	public List<Location> getAllLocation();
	
	public List<Location> getLocationByCategory(int cat_id);
	
	public List<Location> getMyLocation(String deviceId);
	
	public boolean addLocation(Location loc);
	
	public boolean removeLocation(int loc_id);

}
