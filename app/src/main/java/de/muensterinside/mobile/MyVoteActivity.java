package de.muensterinside.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.entities.Vote;
import de.muensterinside.mobile.tasks.MyLocationTask;
import de.muensterinside.mobile.tasks.MyVoteTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class MyVoteActivity extends AppCompatActivity {

    public static final String TAG ="MyVoteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_my_vote);

        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int device_id = sharedPreferences.getInt("deviceId", 0);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            MyVoteTask myVoteTask = new MyVoteTask(this, myApp, device_id);
            myVoteTask.execute();

            List<Location> votes;
            try {
                votes = myVoteTask.get();
            } catch (Exception e) {
                votes = null;
                e.printStackTrace();
            }

            if (votes == null) {
                CharSequence text = "Bisher keine Votes abgegeben.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
                Log.d(TAG, "Keine Liste mit Votes gefunden.");
            } else {
                List myList = new ArrayList<String>();
                for (int i = 0; i < votes.size(); i++) {
                    myList.add(votes.get(i).getName());
                }

                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);

                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        else{
            Log.d(TAG, "Keine Internetverbindung");
            Toast.makeText(MyVoteActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }
    }
}
