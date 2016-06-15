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
import android.widget.ListView;

import de.muensterinside.mobile.tasks.LocationListTask;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 * @author Julia Bracht, Nicolas Burchert
 */
public class CategoryActivity extends AppCompatActivity {
    public static final String TAG = "CategoryActivity";
    private int cat_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_category);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();

        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Category zugegriffen,
         * damit wir auch nur die Locations laden,
         * die zu der ausgewählten Category gehören.
         */
        SharedPreferences boolPref = getSharedPreferences("MyBoolPref", Context.MODE_PRIVATE);
        if(boolPref.getBoolean("newLocationBool", false)== true){
            cat_id = boolPref.getInt("cat_id", 0);
        }
        else {
            cat_id = intent.getIntExtra("selected", 0);
        }

        /* In der SharedPreference wird die vorher ausgewählte
         * ID einer Kategorie gespeichert.
         */
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("catId", cat_id);
        editor.commit();



        // Es wird ein Application Objekt erzeugt
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        // Es wird eine ListView erzeugt, um eine Liste von Locations anzuzeigen
        ListView listView = (ListView) findViewById(R.id.categoryList);

        // Es wird ein Button erzeugt, um eine neue Location anlegen zu können
        Button newLocation = (Button) findViewById(R.id.newLocation);

        // Der LocationListTask wird aufgerufen
        LocationListTask locationListTask = new LocationListTask(this, cat_id, myApp, listView, newLocation);
        locationListTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        //Hier füllen (inflate) wir das Options Menu mit dem Menüeintrag,
        // den wir in der XML-Datei menu_menu_main.xml definiert haben.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() gestartet");
        //Hier prüfen wir, ob unser Menüeintrag angeklickt wurde und führen die gewünschte Aktion aus.
        if (item.getItemId() == R.id.action_settings) {
            //Beim Klicken auf dem Button "Einstellung" öffnet es die passende Activity
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        else if(item.getItemId() == R.id.home_button) {
            //Beim Klicken auf dem Button "Startseite" öffnet es die passende Activity
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
