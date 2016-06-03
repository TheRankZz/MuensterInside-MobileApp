package de.muensterinside.mobile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import de.muensterinside.mobile.tasks.CategoryTask;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);

        // Hier wird das Aussehen der MainActivity festgelegt
        setContentView(R.layout.activity_main);

        // Application Objekt wird erzeugt
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        // ListView zum anzeigen der Kategorien wird erzeugt
        final GridView gridView = (GridView) findViewById(R.id.gridView);

        SharedPreferences boolPref = getSharedPreferences("MyBoolPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = boolPref.edit();
        editor1.putBoolean("newLocationBool", false);
        editor1.commit();

        SharedPreferences boolPref1 = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = boolPref1.edit();
        editor2.putBoolean("newCommentBool", false);
        editor2.commit();

        // Der CategoryTask wird ausgeführt
        CategoryTask categoryTask = new CategoryTask(this, myApp, gridView);
        categoryTask.execute();
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
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
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