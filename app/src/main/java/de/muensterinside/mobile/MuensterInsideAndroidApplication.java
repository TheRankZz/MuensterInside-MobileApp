package de.muensterinside.mobile;


import android.app.Application;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.mock.MobileWebserviceImpl;
import de.muensterinside.mobile.mock.MuensterInsideImplMock;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MuensterInsideAndroidApplication extends Application{

    private Category category;
    private Location location;
    private Comment comment;
    private MobileWebserviceImpl muensterInsideMobile;

    /**
     * Konstruktor
     * Serverinterface wird implementiert.
     */
    public MuensterInsideAndroidApplication() {
        this.muensterInsideMobile = new MuensterInsideImplMock();

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
    /**
     * Gibt das implementierte Serverinterface zurück
     */

    public MobileWebserviceImpl getMuensterInsideMobile() throws Exception{
        return this.muensterInsideMobile;
    }

}
