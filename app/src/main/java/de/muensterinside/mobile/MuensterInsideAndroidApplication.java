package de.muensterinside.mobile;


import android.app.Application;

import de.muensterinside.mobile.interfaces.CategoryService;
import de.muensterinside.mobile.interfaces.LocationService;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;
/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MuensterInsideAndroidApplication extends Application{

    private Category category;
    private Location location;
    private CategoryService categoryService;
    private LocationService locationService;

    /**
     * Konstruktor
     * Serverinterface wird implementiert.
     */
    public MuensterInsideAndroidApplication() {
        this.categoryService = new MuensterInsideImplMock();
        this.locationService = new MuensterInsideLocationImplMock();
    }

    // Gibt die Category zurück
    public Category getCategory() {
        return this.category;
    }

    // Gibt die Location zurück
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gibt das implementierte Serverinterface zurück
     */
    public CategoryService getCategoryService() {
        return this.categoryService;
    }
    public LocationService getLocationService() {
        return this.locationService;
    }

}
