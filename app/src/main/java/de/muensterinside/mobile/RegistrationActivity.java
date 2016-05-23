package de.muensterinside.mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class RegistrationActivity extends AppCompatActivity {
    EditText username;
    MuensterInsideAndroidApplication myApp;
    String android_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

         myApp = (MuensterInsideAndroidApplication) getApplication();

        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        username = (EditText) findViewById(R.id.editTextUsername);



        Button registration = (Button) findViewById(R.id.buttonRegistration);
        assert registration != null;
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = username.getText().toString();
                    final Device device = myApp.getMuensterInsideMobile().register(android_id, name);
                    Intent myIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                    myIntent.setClassName(getPackageName(), getPackageName() + ".MainActivity");
                    myIntent.putExtra("androidId", device.getAndroidUuid());
                    myIntent.putExtra("username", device.getUsername());
                    startActivity(myIntent);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
