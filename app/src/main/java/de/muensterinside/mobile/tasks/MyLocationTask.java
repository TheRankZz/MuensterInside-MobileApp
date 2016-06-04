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
    private ArrayAdapter<String> adapter;
    private String androidId;
    private String username;

    public MyLocationTask(Context context, MuensterInsideAndroidApplication myApp,String androidId, String username, ListView listView)

    {
        this.myApp = myApp;
        this.listView = listView;
        this.context = context;
        this.androidId = androidId;
        this.username = username;
    }

    @Override
    protected List<Location> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        Device device;
        try{
            device = myApp.getMuensterInsideMobile().register(androidId,username);
            locations = myApp.getMuensterInsideMobile().getMyLocations(device.getId());
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

        List myList = new ArrayList<String>();
        for(int i=0; i < locations.size(); i++){
            myList.add(locations.get(i).getName());
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(context, R.layout.content_item_list_category, myList);



        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

}
