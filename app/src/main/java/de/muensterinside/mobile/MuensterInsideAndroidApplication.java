package de.muensterinside.mobile;


import android.app.Application;
import android.support.v7.app.ActionBarDrawerToggle;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.mock.MobileWebserviceImpl;
import de.muensterinside.mobile.mock.MuensterInsideImpl;
import de.muensterinside.mobile.mock.MuensterInsideImplMock;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class MuensterInsideAndroidApplication extends Application{

    private Category category;
    private Location location;
    private String username;
    private Comment comment;
    private Device device;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MobileWebserviceImpl muensterInsideImpl;

    /**
     * Konstruktor
     * Serverinterface wird implementiert.
     */
    public MuensterInsideAndroidApplication() {
        this.muensterInsideImpl = new MuensterInsideImpl(); // MuensterInsideImpl

    }

    /**
     * Gibt die Kategorie aus
     * @return die Kategorie
     * @throws Exception falls zurückgeben der Kategorie nicht erfolgreich war
     */
    public Category getCategory() throws Exception{
        return this.category;
    }

    /**
     * Setzt den UserName
     * @param username Neuer UserName
     * @throws Exception falls das Setzten des UserNames nicht erfolgreich war
     */
    public void setUsername(String username) throws Exception{
        this.username = username;
    }

    /**
     * Gibt den UserName zurück
     * @return username
     * @throws Exception falls das Zurückgeben des UserNames nicht erfolgreich war
     */
    public String getUsername()throws Exception{
        return username;
    }

    /**
     * Gibt die Location zurück
     * @return location
     * @throws Exception falls das Zurückgeben der Location nicht erfolgreich war
     */
    public Location getLocation() throws Exception{
        return this.location;
    }

    /**
     * Gibt den Kommentar zurück
     * @return comment
     * @throws Exception falls das Zurückgeben des Kommentar nicht erfolgreich war
     */
    public Comment getComment() throws Exception{
        return this.comment;
    }

    /**
     * Setzt die Device
     * @param device Neue Device
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * Gibt die Device zurück
     * @return device
     * @throws Exception falls das Zurückgeben der Device nicht erfolgreich war
     */
    public Device getDevice() throws Exception{
        return this.device;
    }

    /**
     *
     * @return
     */
    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    /**
     *
     * @param actionBarDrawerToggle
     */
    public void setActionBarDrawerToggle(ActionBarDrawerToggle actionBarDrawerToggle) {
        this.actionBarDrawerToggle = actionBarDrawerToggle;
    }

    /**
     * Gibt das implementierte Serverinterface zurück
     * @return muensterInsideImpl
     * @throws Exception falls Zurückgeben des Serverinterface nicht erfolgreich war
     */
    public MobileWebserviceImpl getMuensterInsideImpl() throws Exception{
        return this.muensterInsideImpl;
    }

}
