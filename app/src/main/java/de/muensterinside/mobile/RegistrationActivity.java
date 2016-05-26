package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        username = (EditText) findViewById(R.id.registration_username);


        Button registration = (Button) findViewById(R.id.registration);
        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = username.getText().toString();
                    LoginTask loginTask = new LoginTask(view.getContext());
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
                    RegistrationTask registrationTask = new RegistrationTask(view.getContext());
                    registrationTask.execute(android_id,name);

                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private class LoginTask extends AsyncTask<String, Integer, Device>{
        private Context context;
        public LoginTask(Context context){
            this.context = context;
        }

        @Override
        protected Device doInBackground(String... params){
            String androidId = params[0];
            String username = params[1];
            MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
            try {
                Device device = myApp.getMuensterInsideMobile().login(androidId);
                return device;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Device device){
            if(device != null){
                Intent myIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".MainActivity");
                startActivity(myIntent);

                CharSequence text = "Login erfolgreich! Benutzername: " + device.getUsername() + "AndroidId: " + device.getAndroidUuid();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                CharSequence text = "Login fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    private class RegistrationTask extends AsyncTask<String, Integer, Device>{
        private Context context;
        SharedPreferences sharedpreferences;

        public RegistrationTask(Context context){
            this.context = context;
        }

        @Override
        protected Device doInBackground(String... params){
            String androidId = params[0];
            String username = params[1];
            MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
            try {
                Device device = myApp.getMuensterInsideMobile().register(androidId, username);
                return device;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Device device){
            if(device != null){
                MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
                myApp.setDevice(device);

                sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("androidId", device.getAndroidUuid());
                editor.putString("username", device.getUsername());
                editor.commit();

                CharSequence text = "Registrierung erfolgreich! Registrierter Benutzername: " + device.getUsername() + " Registrierte AndroidId: " + device.getAndroidUuid();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                CharSequence text = "Registrierung fehlgeschlagen!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
