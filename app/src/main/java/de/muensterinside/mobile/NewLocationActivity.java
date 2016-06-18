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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.NewLocationTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public class NewLocationActivity extends AppCompatActivity {
    public static final String TAG = "NewLocationActivity";
    private Context context;
    private EditText name;
    private EditText description;
    private EditText link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate) gestartet");
        super.onCreate(savedInstanceState);

        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_new_location);

        //Eingabefeld für den Namen wird erzeugt
        name = (EditText) findViewById(R.id.locationName);

        // Eingabefeld für die Beschreibung wird erzeugt
        description = (EditText) findViewById(R.id.locationDescription);

        // Eingabefeld für den Link wird erzeugt
        link = (EditText) findViewById(R.id.locationLink);

          /* In der SharedPreference wird die vorher ausgewählte
         * ID einer Kategorie, die Identifikation des Gerätes und der Username ausgelesen.
         */
        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final int cat_id = myCatIdPref.getInt("catId", 1);
        final int device_id = sharedPreferences.getInt("deviceId", 0);
        context = this;


        Button saveLocation = (Button) findViewById(R.id.confirmLocation);
        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "saveLocation.onClick() gestartet");
                Intent myIntent = new Intent(NewLocationActivity.this, MainActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".MainActivity");

                MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
                String locationName = name.getText().toString();
                String locationDescription = description.getText().toString();
                String locationLink = link.getText().toString();


                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    NewLocationTask newLocationTask = new NewLocationTask(context, myApp,
                            locationName, locationDescription, locationLink, cat_id, device_id);
                    newLocationTask.execute();
                    int code = 1;
                    try {
                        code = newLocationTask.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (code == 0) {

                        SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = pref.edit();
                        edit.putBoolean("test", true);
                        edit.apply();

                        CharSequence text = "Location " + locationName + " wurde erstellt.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Location wurde erfolgreich erstellt");

                    } else {
                        CharSequence text = "Location " + locationName + " wurde nicht erstellt.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Location wurde nicht erfolgreich erstellt");
                    }
                    startActivity(myIntent);
                }
                else{
                    Log.d(TAG, "Keine Internetverbindung");
                    Toast.makeText(NewLocationActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
                }
            }
        });
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
