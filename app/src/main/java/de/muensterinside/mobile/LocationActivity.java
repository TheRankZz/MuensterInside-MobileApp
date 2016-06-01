package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.LocationCommentTask;
import de.muensterinside.mobile.tasks.LocationTask;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class LocationActivity extends AppCompatActivity {
    public static final String TAG = "LocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_location);

        // Die von der CategoryActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();

        // Ein Application Objekt wird erzeugt
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Location zugegriffen,
         * damit wir auch nur die ausgewählte Location laden.
         */
        int loc_id;

        SharedPreferences bool = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
        if(bool.getBoolean("newCommentBool", false)==true){
            loc_id = bool.getInt("loc_id", 0);
        }
        else {
            loc_id = intent.getIntExtra("loc_id", 0);
        }



        /* In der MainActivity wurde mittels myIntent.putExtra() die vom User ausgewählte
         * ID der Kategorie an die CategoryActivity übergeben.
         * In der CategoryActivity wurde diese ID in eine SharedPreference gespeichert.
         * Diese wird hier nun in die Variable cat_id gespeichert.
         */
        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        int cat_id = myCatIdPref.getInt("catId", 0);

        // TextView wird erzeugt
        TextView exampleName = (TextView) findViewById(R.id.textViewExampleName);

        // TextView wird erzeugt
        final TextView exampleVote = (TextView) findViewById(R.id.textViewExampleVote);

        // TextView wird erzeugt
        TextView exampleDescription = (TextView) findViewById(R.id.textViewExampleDescription);

        ListView kommentare = (ListView) findViewById(R.id.kommentare);


        Button b = (Button) findViewById(R.id.button1);

        // Button für den Upvote wird erzeugt
        Button up = (Button) findViewById(R.id.up);

        // Button für den Downvote wird erzeugt
        Button down = (Button) findViewById(R.id.down);
        Button c = (Button) findViewById(R.id.KommentarAnzeigen);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String deviceId = sharedPreferences.getString("androidId", "Default");
        String username = sharedPreferences.getString("username", "Default");

        // LocationTask wird aufgerufen
        LocationTask locationTask = new LocationTask(this, loc_id, cat_id, myApp, exampleName,
                exampleVote, exampleDescription, b, up, down, c, deviceId, username, kommentare);
        locationTask.execute();
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
        else if(item.getItemId() == R.id.home_button) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
