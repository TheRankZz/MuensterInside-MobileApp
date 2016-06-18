package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.MyLocationTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class MyLocationActivity extends AppCompatActivity {

    public static final String TAG = "MyLocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_my_location);


        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int device_id = sharedPreferences.getInt("deviceId", 0);

        MyLocationTask myLocationTask = new MyLocationTask(this, myApp, device_id);
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
            CharSequence text = "Bisher keine Locations angelegt.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            Log.i(TAG, "Keine Liste mit Locations gefunden.");
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
