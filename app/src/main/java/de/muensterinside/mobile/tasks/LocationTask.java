package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class LocationTask extends AsyncTask<Integer, Void, Location> {
    private MuensterInsideAndroidApplication myApp;
    private Context context;
    private int loc_id;
    private int device_id;
    public static final String TAG = "LocationTask";

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp repräsentiert den Zustand der Application
     * @param loc_id die Identifizitaet der Location
     */
    public LocationTask(Context context,MuensterInsideAndroidApplication myApp, int loc_id,
                        int device_id){
        this.context = context;
        this.loc_id = loc_id;
        this.myApp = myApp;
        this.device_id = device_id;

    }

    // Im Hintergrund wird der Webservice aufgerufen
    @Override
    protected Location doInBackground(Integer... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            /* Die Methode getLocationsByCategory liefert anhand der ID der ausgewählten
             * Kategorie eine Liste mit Locations zurück.
             */
            Location location = myApp.getMuensterInsideImpl().getLocation(this.loc_id);
            boolean isVoted = myApp.getMuensterInsideImpl().isVoted(this.loc_id, device_id);
            location.vote(isVoted);

            Log.i(TAG, "doInBackground() erfolgreich");
            return location;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

}
