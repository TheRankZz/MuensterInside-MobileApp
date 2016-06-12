package de.muensterinside.mobile.tasks;

import android.content.Context;

import android.os.AsyncTask;

import android.util.Log;

import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;


/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class NewLocationTask extends AsyncTask<Void, Void, Integer> {
    private MuensterInsideAndroidApplication myApp;
    private Context context;
    private String locationName;
    private String locationDescription;
    private String locationLink;
    private int cat_id;
    private int code = 1;
    public static final String TAG = "NewLocationTask";

    /* Der Konstruktor erwartet ein Context Objekt,
     * ein Application Objekt und ein ListView Objekt.
     */
    public NewLocationTask(Context context, MuensterInsideAndroidApplication myApp,
                           String locationName, String locationDescription,
                           String locationLink, int cat_id) {
        this.context = context;
        this.myApp = myApp;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationLink = locationLink;
        this.cat_id = cat_id;
    }

    // Im Hintergrund soll der Webservice aufgerufen werden
    @Override
    protected Integer doInBackground(Void... params) {
        Log.d(TAG, "doInBackground() gestartet");
        try {
            Device d = myApp.getDevice();
            int temp_cat_id = this.cat_id + 1;
            code = myApp.getMuensterInsideImpl().saveLocation(locationName,locationDescription,
                    locationLink,temp_cat_id,d.getId());

            Log.i(TAG, "doInBackground() erfolgreich");
            return code;
        }
        catch (Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return code;
    }

    /* Die onPostExecute Methode erwartet eine Liste,
     * in der Category Objekte gespeichert sind.
     */
    @Override
    protected void onPostExecute(Integer code) {
        Log.d(TAG, "onPostExecute() gestartet");



    }


}

