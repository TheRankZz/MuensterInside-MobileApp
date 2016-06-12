package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.MyLocationTask;

public class MyLocationActivity extends AppCompatActivity {

    public static final String TAG = "MyLocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);


        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);

        MyLocationTask myLocationTask = new MyLocationTask(this, myApp);
        myLocationTask.execute();

        List<Location> locations;
        try{
            locations = myLocationTask.get();
        }
        catch (Exception e){
            locations = null;
            e.printStackTrace();
        }

        if(locations == null){
            Log.d(TAG, "Keine Liste mit Locations gefunden.");
        }
        else {
            List myList = new ArrayList<String>();
            for (int i = 0; i < locations.size(); i++) {
                myList.add(locations.get(i).getName());
            }

            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
