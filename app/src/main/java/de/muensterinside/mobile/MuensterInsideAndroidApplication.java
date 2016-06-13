package de.muensterinside.mobile;


import android.app.Application;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.mock.MobileWebserviceImpl;
import de.muensterinside.mobile.mock.MuensterInsideImpl;
import de.muensterinside.mobile.mock.MuensterInsideImplMock;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MuensterInsideAndroidApplication extends Application{

    private Category category;
    private Location location;
    private Comment comment;
    private Device device;
    private MobileWebserviceImpl muensterInsideImpl;

    /**
     * Konstruktor
     * Serverinterface wird implementiert.
     */
    public MuensterInsideAndroidApplication() {
        this.muensterInsideImpl = new MuensterInsideImplMock(); // MuensterInsideImpl

    }

    // Gibt die Category zurück
    public Category getCategory() throws Exception{
        return this.category;
    }

    // Gibt die Location zurück
    public Location getLocation() throws Exception{
        return this.location;
    }

    public Comment getComment() throws Exception{
        return this.comment;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Device getDevice() throws Exception{
        return this.device;
    }

    /**
     * Gibt das implementierte Serverinterface zurück
     */
    public MobileWebserviceImpl getMuensterInsideImpl() throws Exception{
        return this.muensterInsideImpl;
    }

}
