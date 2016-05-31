package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import de.muensterinside.mobile.tasks.LocationListTask;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class CategoryActivity extends AppCompatActivity {
    public static final String TAG = "CategoryActivity";
    private int cat_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_category);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();

        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Category zugegriffen,
         * damit wir auch nur die Locations laden,
         * die zu der ausgewählten Category gehören.
         */
        cat_id = intent.getIntExtra("selected", 0);

        /* In einer SharedPreference wird die vorher ausgewählte
         * ID einer Kategorie gespeichert.
         */
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("catId", cat_id);
        editor.commit();

        // Es wird ein Application Objekt erzeugt
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        // Es wird eine ListView erzeugt um eine Liste von Locations anzuzeigen
        GridView gridView = (GridView) findViewById(R.id.gridView);

        // Es wird ein Button erzeugt um eine neue Location anlegen zu können
        Button newLocation = (Button) findViewById(R.id.newLocation);

        // Der LocationListTask wird aufgerufen
        LocationListTask locationListTask = new LocationListTask(this, cat_id, myApp, gridView, newLocation);
        locationListTask.execute();
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
        else
            return super.onOptionsItemSelected(item);
    }
}
