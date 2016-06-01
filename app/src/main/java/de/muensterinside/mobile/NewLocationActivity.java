package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.muensterinside.mobile.entities.Device;

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

        // Aussehen der Activity wird festgelegt
        setContentView(R.layout.activity_new_location);

        //Eingabefeld für den Namen wird erzeugt
        name = (EditText) findViewById(R.id.locationName);

        // Eingabefeld für die Beschreibung wird erzeugt
        description = (EditText) findViewById(R.id.locationDescription);

        // Eingabefeld für den Link wird erzeugt
        link = (EditText) findViewById(R.id.locationLink);

        /* Die ID der ursprünglich ausgewählten Kategorie,
         * die jeweilige Android Device-ID und
         * den ausgewählten Benutzernamen werden aus den SharedPreferences geholt.
         */
        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final int cat_id = myCatIdPref.getInt("cat_id", 1);
        final String android_id = sharedPreferences.getString("android_id", "Default");
        final String username = sharedPreferences.getString("username", "Default");
        context = this;


        Button saveLocation = (Button) findViewById(R.id.confirmLocation);
        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "saveLocation.onClick() gestartet");
                Intent myIntent = new Intent(NewLocationActivity.this, MainActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".MainActivity");

                Device device;
                MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
                String locationName = name.getText().toString();
                String locationDescription = description.getText().toString();
                String locationLink = link.getText().toString();

                try {
                    device = myApp.getMuensterInsideMobile().register(android_id,username);
                    myApp.getMuensterInsideMobile().saveLocation(locationName,locationDescription,locationLink,cat_id,device.getId());

                    CharSequence text = "Location " +locationName+ " wurde erstellt.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.i(TAG, "Location wurde erfolgreich erstellt");
                }
                catch(Exception e){
                    Log.e(TAG, "Location wurde nicht erfolgreich erstellt");
                    e.printStackTrace();

                    CharSequence text = "Location wurde nicht erstellt.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                startActivity(myIntent);
            }
        });
    }
}
