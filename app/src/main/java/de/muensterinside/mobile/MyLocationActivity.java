package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.adapter.LocationListViewAdapters;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.MyLocationTask;

import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class MyLocationActivity extends AppCompatActivity {

    public static final String TAG = "MyLocationActivity";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_my_location);


        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int device_id = sharedPreferences.getInt("deviceId", 0);

        context = this;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            MyLocationTask myLocationTask = new MyLocationTask(this, myApp, device_id);
            myLocationTask.execute();

            List<Location> locations;
            try {
                locations = myLocationTask.get();
            }
            catch (Exception e) {
                locations = null;
                e.printStackTrace();
            }

            if (locations == null) {
                CharSequence text = "Bisher keine Locations angelegt.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
                Log.i(TAG, "Keine Liste mit Locations gefunden.");
            }
            else {
                /* Es wird eine ArrayList vom Typ HashMap<String, String> erzeugt.
                 * In einer jeden HashMap wird der Name und der VoteValue
                 * einer jeden Location gespeichert.
                 */
                LocationListViewAdapters adapter;
                Location location;
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
                adapter = new LocationListViewAdapters(this, list);

                listView.setAdapter(adapter);

                final List<Location> test = locations;

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
            }
        }
        else{
            Log.e(TAG, "Keine Internetverbindung");
            Toast.makeText(MyLocationActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() gestartet");
        //Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        // Home Button
        else if(item.getItemId() == R.id.action_home){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
