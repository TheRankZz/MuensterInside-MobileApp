package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class RegistrationTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "RegistrationTask";
    private boolean isRegistered = false;
    private Button registration;

    /* Der Konstruktor erwartet ein Context Objekt
     * und ein Application Objekt.
     */
    public RegistrationTask(Context context, MuensterInsideAndroidApplication myApp, Button registration){
        this.context = context;
        this.myApp = myApp;
        this.registration = registration;
    }

    // Im Hintergrund soll der Webservice aufgerufen werden.
    @Override
    protected Device doInBackground(String... params){
        Log.d(TAG, "doInBackground() gestartet");
        String android_id = params[0];
        String username = params[1];
        try {
            /* Die register Methode liefert anhand der Android Device-ID
             * und des Usernames ein Device Objekt.
             */
            Device loginDevice = this.myApp.getMuensterInsideMobile().login(android_id);
            if(loginDevice != null){
                this.isRegistered = true;
                return null;
            }
            else {
                Device device = this.myApp.getMuensterInsideMobile().register(android_id, username);
                Log.i(TAG, "doInBackground() erfolgreich");
                return device;
            }
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    /* Die onPostExecute Methode erwartet ein Device Objekt von der doInBackground Methode.
     * Mittels Toasts wird der User über den Erfolg/Misserfolg informiert.
     */
    protected void onPostExecute(Device device){
        Log.d(TAG, "onPostExecute() gestartet");
        if(device != null){
            CharSequence text = "Registrierung erfolgreich! Registrierter Benutzername: "
                    + device.getUsername() + " Registrierte AndroidId: " + device.getAndroidUuid();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            registration.setVisibility(View.INVISIBLE);
            Log.i(TAG, "Registrierung erfolgreich");
        }
        else if(isRegistered == true){
            CharSequence text = "Sie sind schon registriert.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.e(TAG, "AndroidId ist schon registriert");
        }
        else {
            CharSequence text = "Registrierung fehlgeschlagen.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.e(TAG, "Registrierung fehlgeschlagen");
        }
    }
}
