package de.muensterinside;


import android.app.Application;

import de.muensterinside.interfaces.CategoryService;
import de.muensterinside.interfaces.LocationService;
import de.muensterinside.modals.Category;
import de.muensterinside.modals.Location;

public class MuensterInsideAndroidApplication extends Application{

    // holds the category
    private Category category;

    //holds the location
    private Location location;

    private CategoryService categoryService;
    private LocationService locationService;

    /**
     * Constructor
     * Sets the implementation of the ServerInterface to our mock class.
     */
    public MuensterInsideAndroidApplication() {
        this.categoryService = new MuensterInsideImplMock();
        this.locationService = new MuensterInsideLocationImplMock();
    }

    // return the category
    public Category getCategory() {
        return this.category;
    }

    // return the location
    public Location getLocation() {
        return this.location;
    }

    /**
     * Get the implementation of the Server Interface.
     * @return an object which implements the server interface
     */
    public CategoryService getCategoryService() {
        return this.categoryService;
    }
    public LocationService getLocationService() {
        return this.locationService;
    }

}
