package de.muensterinside.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import de.muensterinside.mobile.tasks.LoginTask;
import de.muensterinside.mobile.tasks.RegistrationTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class RegistrationActivity extends AppCompatActivity {
    private EditText username;
    private MuensterInsideAndroidApplication myApp;
    private String android_id;
    public static final String TAG = "RegistrationActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myApp = (MuensterInsideAndroidApplication) getApplication();

        // Die Android Device-ID wird ermittelt
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        // Hier wird der vom Benutzer eingegene Benutzername gespeichert
        username = (EditText) findViewById(R.id.registration_username);

        /* SharedPreferences wird benutzt um,
         * die androidId und den usernamen unter dem Schlüssel "MyPref",
         * zu speichern
         */
        SharedPreferences sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("androidId", android_id);
        editor.putString("username", username.getText().toString());
        editor.commit();

        // Button für die Registrierung wird angelegt
        Button registration = (Button) findViewById(R.id.registration);

        // Button für das Login wird angelegt
        Button login = (Button) findViewById(R.id.login);

        /* Wenn der Button login gedrückt wurde,
         * soll der LoginTask ausgeführt werden.
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "login.onClick() gestartet");
                try {
                    String name = username.getText().toString();
                    LoginTask loginTask = new LoginTask(view.getContext(),myApp);
                    loginTask.execute(android_id, name);
                    Log.i(TAG, "login.onClick() erfolgreich");
                }
                catch(Exception e){
                    Log.e(TAG, "login.onClick() fehlgeschlagen");
                    e.printStackTrace();
                }

            }
        });

        /* Wenn der Button registration gedrückt wurde,
         * soll der RegistrationTask ausgeführt werden
         */
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "registration.onClick() gestartet");
                try {
                    String name = username.getText().toString();
                    RegistrationTask registrationTask = new RegistrationTask(view.getContext(), myApp);
                    registrationTask.execute(android_id,name);
                    Log.i(TAG, "registration.onClick() erfolgreich");
                }
                catch(Exception e){
                    Log.e(TAG, "registration.onClick() fehlgeschlagen");
                    e.printStackTrace();
                }

            }
        });
    }




}
