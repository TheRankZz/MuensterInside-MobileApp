package de.muensterinside.mobile;


import android.app.Application;

import de.muensterinside.mobile.mock.MuensterInsideMobile;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.mock.MuensterInsideImplMock;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MuensterInsideAndroidApplication extends Application{

    private Category category;
    private Location location;
    private Comment comment;
    private MuensterInsideMobile muensterInsideMobile;

    /**
     * Konstruktor
     * Serverinterface wird implementiert.
     */
    public MuensterInsideAndroidApplication() {
        this.muensterInsideMobile = new MuensterInsideImplMock();

    }

    // Gibt die Category zurück
    public Category getCategory() {
        return this.category;
    }

    // Gibt die Location zurück
    public Location getLocation() {
        return this.location;
    }

    //Gibt die Kommentare zurück
    public Comment getComment() {return this.comment;}

    /**
     * Gibt das implementierte Serverinterface zurück
     */

    public MuensterInsideMobile getMuensterInsideMobile() { return this.muensterInsideMobile;}

}
