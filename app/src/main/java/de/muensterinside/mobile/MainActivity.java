package de.muensterinside.mobile;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        final ListView listView = (ListView) findViewById(R.id.listView);
        CategoryTask categoryTask = new CategoryTask(this, myApp, listView);
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
        else
            return super.onOptionsItemSelected(item);
    }

}