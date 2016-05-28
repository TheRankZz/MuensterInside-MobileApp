package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.muensterinside.mobile.entities.Device;

public class NewLocationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        EditText name = (EditText) findViewById(R.id.locationName);
        final String locationName = name.getText().toString();

        EditText description = (EditText) findViewById(R.id.locationDescription);
        final String locationDescription = description.getText().toString();

        EditText link = (EditText) findViewById(R.id.locationLink);
        final String locationLink = link.getText().toString();

        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final int cat_id = myCatIdPref.getInt("catId", 1);
        final String androidId = sharedPreferences.getString("androidId", "Default");
        final String username = sharedPreferences.getString("username", "Default");
        final Context context = this;


        Button saveLocation = (Button) findViewById(R.id.confirmLocation);
        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NewLocationActivity.this, MainActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".MainActivity");

                Device device;
                MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

                try {
                    device = myApp.getMuensterInsideMobile().register(androidId,username);
                    myApp.getMuensterInsideMobile().saveLocation(locationName,locationDescription,locationLink,cat_id,device.getId());

                    CharSequence text = "Location " +locationName+ " wurde erstellt.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                catch(Exception e){
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