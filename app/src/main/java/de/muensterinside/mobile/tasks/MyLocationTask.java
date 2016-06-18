package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class MyLocationTask extends AsyncTask<Void,Void, List<Location>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "MyLocationTask";
    private ListView listView;
    private List<Location> locations;
    private int device_id;


    /**
     * Konstruktor
     * @param context der Inhalt Activity
     * @param myApp repräsentiert den Zustand der Application
     */
    public MyLocationTask(Context context, MuensterInsideAndroidApplication myApp, int device_id)

    {
        this.myApp = myApp;
        this.context = context;
        this.device_id = device_id;
    }

    // Im Hintergrund wird der Webservice aufgerufen
    @Override
    protected List<Location> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        try{
            locations = this.myApp.getMuensterInsideImpl().getMyLocations(device_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return locations;
        }
        catch(Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }

        return null;
    }

}
