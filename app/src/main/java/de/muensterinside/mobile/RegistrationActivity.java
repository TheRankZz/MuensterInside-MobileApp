package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.tasks.LoginTask;
import de.muensterinside.mobile.tasks.RegistrationTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class RegistrationActivity extends AppCompatActivity {
    private EditText username;
    private MuensterInsideAndroidApplication myApp;
    private String android_id;
    private Context context;
    public static final String TAG = "RegistrationActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_registration);

        myApp = (MuensterInsideAndroidApplication) getApplication();

        // Die Android Device-ID wird ermittelt
        android_id = Settings.Secure.getString(getApplicationContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i(TAG, "Android Device-ID: " + android_id);

        // Hier wird der vom Benutzer eingegene Benutzername gespeichert
        username = (EditText) findViewById(R.id.registration_username);

        context = this;


        // Wenn ein Gerät schon registriert ist, wird beim Start automatisch ein Login durchgeführt
        LoginTask loginTask = new LoginTask(this, myApp, android_id);
        loginTask.execute();

        Device device;
        try {
            device = loginTask.get();
            Log.i(TAG, "login.onClick() erfolgreich");
        }
        catch(Exception e){
            Log.e(TAG, "login.onClick() fehlgeschlagen");
            device = null;
            e.printStackTrace();
        }

        if(device != null){
            String name = device.getUsername();

            /* In der SharedPreference wird die vorher ausgewählte
             * AnroidID des Gerätes und der Username gespeichert.
             */
            SharedPreferences sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("androidId", android_id);
            editor.putString("username", name);
            editor.putInt("deviceId", device.getId());
            editor.putBoolean("test", false);
            editor.apply();

            Intent myIntent = new Intent(context, MainActivity.class);
            myIntent.putExtra("username", name);
            startActivity(myIntent);

            CharSequence text = "Login erfolgreich! Benutzername: " + device.getUsername()
                    + "AndroidId: " + device.getAndroidUuid();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.i(TAG, "Login erfolgreich");
        }
        else {
            CharSequence text = "Login fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.i(TAG, "Login fehlgeschlagen");
        }

        // Button für die Registrierung wird angelegt
        final Button registration = (Button) findViewById(R.id.registration);

        // Button für das Login wird angelegt
        Button login = (Button) findViewById(R.id.login);


        /* Wenn der Button login gedrückt wurde,
         * soll der LoginTask ausgeführt werden.
         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "login.onClick() gestartet");

                LoginTask loginTask = new LoginTask(view.getContext(), myApp, android_id);
                loginTask.execute();
                Device device;
                try {
                    device = loginTask.get();
                    Log.i(TAG, "login.onClick() erfolgreich");
                }
                catch(Exception e){
                    Log.e(TAG, "login.onClick() fehlgeschlagen");
                    device = null;
                    e.printStackTrace();
                }

                if(device != null){
                    String name = device.getUsername();
                    Log.i(TAG, "Username: " + name);

                    /* In der SharedPreference wird die vorher ausgewählte
                     * AnroidID des Gerätes und der Username gespeichert.
                     */
                    SharedPreferences sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("androidId", android_id);
                    editor.putString("username", name);
                    editor.putInt("deviceId", device.getId());
                    editor.putBoolean("test", false);
                    editor.apply();

                    Intent myIntent = new Intent(context, MainActivity.class);
                    myIntent.putExtra("username", name);
                    startActivity(myIntent);

                    CharSequence text = "Login erfolgreich! Benutzername: " + device.getUsername()
                            + "AndroidId: " + device.getAndroidUuid();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.i(TAG, "Login erfolgreich");
                }
                else {
                    CharSequence text = "Login fehlgeschlagen!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.i(TAG, "Login fehlgeschlagen");
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
                String name = username.getText().toString();
                Log.i(TAG, "Username: " + name);
                RegistrationTask registrationTask = new RegistrationTask(view.getContext(), myApp,
                        android_id, name);
                registrationTask.execute(android_id,name);
                Device device;
                try {
                    device = registrationTask.get();
                    Log.i(TAG, "registration.onClick() erfolgreich");
                }
                catch(Exception e){
                    Log.e(TAG, "registration.onClick() fehlgeschlagen");
                    device = null;
                    e.printStackTrace();
                }

                if(device != null){
                    CharSequence text = "Registrierung erfolgreich! Registrierter Benutzername: "
                            + device.getUsername()
                            + " Registrierte AndroidId: "
                            + device.getAndroidUuid();

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    registration.setVisibility(View.INVISIBLE);
                    Log.i(TAG, "Registrierung erfolgreich");
                }
                else {
                    CharSequence text = "Registrierung fehlgeschlagen.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Log.e(TAG, "Registrierung fehlgeschlagen");
                }
            }
        });
    }
}
