package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class LocationTask extends AsyncTask<Integer, Void, Location> {
    private MuensterInsideAndroidApplication myApp;
    private Context context;
    private int loc_id;
    public static final String TAG = "LocationTask";

    /* Konstruktor erwartet ein Context Objekt,
     * die ID der Location die ausgewählt wurde,
     * die ID der Kategorie die ausgewählt wurde,
     * ein Application Objekt und mehrere TextViews/Buttons
     * für die Darstellung der LocationActivity.
     */
    public LocationTask(Context context,MuensterInsideAndroidApplication myApp, int loc_id){
        this.context = context;
        this.loc_id = loc_id;
        this.myApp = myApp;

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

            Log.i(TAG, "doInBackground() erfolgreich");
            return location;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    // onPostExecute Methode erwartet ein Location Objekt von der doInBackground Methode
    @Override
    public void onPostExecute(Location location){
        Log.d(TAG, "onPostExecute() gestartet");


    }
}
