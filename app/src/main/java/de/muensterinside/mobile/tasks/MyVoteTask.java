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
public class MyVoteTask extends AsyncTask<Void,Void, List<Location>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "MyLocationTask";
    private ListView listView;
    private List<Location> votes;
    private int device_id;

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp der Zustand der Application
     */
    public MyVoteTask(Context context, MuensterInsideAndroidApplication myApp)

    {
        this.myApp = myApp;
        this.context = context;
    }

    // Im Hintergrund wird der Webservice aufgerufen
    @Override
    protected List<Location> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        try{
            device_id = this.myApp.getDevice().getId();
            votes = this.myApp.getMuensterInsideImpl().getMyVotes(device_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return votes;
        }
        catch(Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }

        return null;
    }


}
