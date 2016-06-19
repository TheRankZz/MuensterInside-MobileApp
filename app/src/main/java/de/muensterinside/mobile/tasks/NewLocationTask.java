package de.muensterinside.mobile.tasks;

import android.content.Context;

import android.os.AsyncTask;

import android.util.Log;



import de.muensterinside.mobile.MuensterInsideAndroidApplication;




/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class NewLocationTask extends AsyncTask<Void, Void, Integer> {
    private MuensterInsideAndroidApplication myApp;
    private Context context;
    private String locationName;
    private String locationDescription;
    private String locationLink;
    private int cat_id;
    private int device_id;
    private int code = 1;
    public static final String TAG = "NewLocationTask";

    /**
     * Konstruktor
     * @param context  der Inhalt der Activity
     * @param myApp den Zustand der Application
     * @param locationName der Name der Location
     * @param locationDescription die Beschreibung der Location
     * @param locationLink der Link der Location
     * @param cat_id die Identifizitaet der Kategorie
     */
    public NewLocationTask(Context context, MuensterInsideAndroidApplication myApp,
                           String locationName, String locationDescription,
                           String locationLink, int cat_id, int device_id) {
        this.context = context;
        this.myApp = myApp;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationLink = locationLink;
        this.cat_id = cat_id;
        this.device_id = device_id;
    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    protected Integer doInBackground(Void... params) {
        Log.d(TAG, "doInBackground() gestartet");
        try {

            //Gibt aus ob Speichern der Location erfolgreich war
            int temp_cat_id = this.cat_id + 1;
            code = myApp.getMuensterInsideImpl().saveLocation(locationName,locationDescription,
                    locationLink,temp_cat_id,device_id);

            Log.i(TAG, "doInBackground() erfolgreich");
            return code;
        }
        catch (Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return code;
    }




}

