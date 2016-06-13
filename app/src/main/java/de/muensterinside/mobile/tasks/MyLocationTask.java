package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class MyLocationTask extends AsyncTask<Void,Void, List<Location>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "MyLocationTask";
    private ListView listView;
    private List<Location> locations;
    private int device_id;

    public MyLocationTask(Context context, MuensterInsideAndroidApplication myApp)

    {
        this.myApp = myApp;
        this.context = context;
    }

    @Override
    protected List<Location> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        try{
            device_id = this.myApp.getDevice().getId();
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

    @Override
    protected void onPostExecute(List<Location> locations)
    {
        Log.d(TAG, "onPostExecute() gestartet");

    }
}
