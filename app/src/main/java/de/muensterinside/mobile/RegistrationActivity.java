package de.muensterinside.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import de.muensterinside.mobile.tasks.LoginTask;
import de.muensterinside.mobile.tasks.RegistrationTask;

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
        username = (EditText) findViewById(R.id.registration_username);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("androidId", android_id);
        editor.putString("username", username.getText().toString());
        editor.commit();


        Button registration = (Button) findViewById(R.id.registration);
        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = username.getText().toString();
                    LoginTask loginTask = new LoginTask(view.getContext(),myApp);
                    loginTask.execute(android_id, name);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = username.getText().toString();
                    RegistrationTask registrationTask = new RegistrationTask(view.getContext(), myApp);
                    registrationTask.execute(android_id,name);

                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }




}
