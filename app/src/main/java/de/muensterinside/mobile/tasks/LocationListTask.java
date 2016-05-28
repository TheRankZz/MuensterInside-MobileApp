package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.ListViewAdapters;
import de.muensterinside.mobile.LocationActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.NewLocationActivity;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Device;
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
    private Location l;
    private Category c;

    public LocationListTask(Context context, int cat_id, MuensterInsideAndroidApplication myApp, ListView listView, Button newLocation){
        this.context = context;
        this.cat_id = cat_id;
        this.myApp = myApp;
        this.listView = listView;
        this.newLocation = newLocation;
    }

    @Override
    protected List<Location> doInBackground(Integer... params){
        try {
            locations = myApp.getMuensterInsideMobile().getLocationsByCategory(this.cat_id);

            return locations;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Location> locations){

        ArrayList<HashMap<String, String>> list;
        list = new ArrayList<HashMap<String,String>>();
        for(int i=0; i < locations.size(); i++){
            l = locations.get(i);
            c = l.getCategory();
            if(c.getId() == cat_id){
                HashMap<String,String> temp = new HashMap<String, String>();
                temp.put(FIRST_COLUMN, l.getName());
                temp.put(SECOND_COLUMN, String.valueOf(l.getVoteValue()));
                list.add(temp);

            }
        }


        // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
        adapter = new ListViewAdapters(context, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                Intent myIntent = new Intent(context, LocationActivity.class);
                myIntent.putExtra("loc_id", position);
                context.startActivity(myIntent);
            }
        });


        newLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, NewLocationActivity.class);
                context.startActivity(myIntent);
            }
        });
    }
}