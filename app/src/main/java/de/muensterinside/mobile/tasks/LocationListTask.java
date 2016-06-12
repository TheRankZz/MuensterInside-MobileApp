package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.adapter.ListViewAdapters;
import de.muensterinside.mobile.LocationActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.NewLocationActivity;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;

import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class LocationListTask extends AsyncTask<Integer, Void, List<Location>> {
    private MuensterInsideAndroidApplication myApp;
    private List<Location> locations;
    private ListViewAdapters adapter;
    private Context context;
    private ListView listView;
    private Button newLocation;
    private int cat_id;
    private Location location;
    public static final String TAG = "LocationListTask";

    /* Der Konstruktor erwartet ein Context Objekt,
     * ein Application Objekt, eine ListView und einen Button.
     */
    public LocationListTask(Context context, int cat_id, MuensterInsideAndroidApplication myApp, ListView listView, Button newLocation){
        this.context = context;
        this.cat_id = cat_id;
        this.myApp = myApp;
        this.listView = listView;
        this.newLocation = newLocation;
    }

    // Im Hintergrund soll der Webservice aufgerufen werden
    @Override
    protected List<Location> doInBackground(Integer... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            /* Die Methode getLocationsByCateogry liefert anhand
             * der cat_id eine Liste mit Locations.
             */
            this.cat_id = this.cat_id +1;
            locations = myApp.getMuensterInsideImpl().getLocationsByCategory(this.cat_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return locations;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    /* Die onPostExecute Methode erwartet eine Liste,
     * in der Location Objekte gespeichert sind.
     */
    @Override
    protected void onPostExecute(List<Location> locations){
        Log.d(TAG, "onPostExecute() gestartet");

        /* Es wird eine ArrayList vom Typ HashMap<String, String> erzeugt.
         * In einer jeden HashMap wird der Name und der VoteValue
         * einer jeden Location gespeichert.
         */
        ArrayList<HashMap<String, String>> list;
        list = new ArrayList<HashMap<String,String>>();
        for(int i=0; i < locations.size(); i++){
            location = locations.get(i);
            HashMap<String,String> temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, location.getName());
            temp.put(SECOND_COLUMN, String.valueOf(location.getVoteValue()));
            list.add(temp);
        }


        // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
        adapter = new ListViewAdapters(context, list);

        listView.setAdapter(adapter);
        final List<Location> test = locations;

        /* Wenn ein Eintrag in der Liste ausgewählt wird,
         * soll die LocationActivity starten. Anhand der
         * mitgegebenen loc_id, soll die richtige Location in der
         * LocationActivity angezeigt werden.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                Log.d(TAG, "listView.onItemClick() gestartet");
                Intent myIntent = new Intent(context, LocationActivity.class);
                Location l = test.get(position);
                int choice = l.getId();
                myIntent.putExtra("loc_id", choice);
                //myIntent.putExtra("loc_id", position);
                context.startActivity(myIntent);
            }
        });

        // Wenn der Button newLocation angeklickt wurde, soll die NewLocationActivity aufgerufen werden
        newLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "newLocation.onClick() gestartet");
                Intent myIntent = new Intent(context, NewLocationActivity.class);
                context.startActivity(myIntent);
            }
        });
    }
}
