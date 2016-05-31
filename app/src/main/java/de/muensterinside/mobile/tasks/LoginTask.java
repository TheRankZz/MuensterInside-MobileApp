package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import de.muensterinside.mobile.MainActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class LoginTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public final static String TAG = "LoginTask";

    /* Der Konstruktor erwartet ein Context Objekt
     * und ein Application Objekt
     */
    public LoginTask(Context context, MuensterInsideAndroidApplication myApp){
        this.context = context;
        this.myApp = myApp;
    }

    // Aufruf des Webservice soll im Hintergrund geschehen
    @Override
    protected Device doInBackground(String... params){
        Log.d(TAG, "doInBackground() gestartet");
        String androidId = params[0];
        String username = params[1];
        try {
            // Die Login Methode liefert anhand der Android Device-ID ein Device Objekt
            Device device = myApp.getMuensterInsideMobile().login(androidId);
            Log.i(TAG, "doInBackground() erfolgreich");
            return device;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }
    /* Die onPostExecute Methode bekommt von der doInBackground Methode
     * ein Device Objekt. Wenn es != null ist soll die MainActivity aufgerufen werden
     * und der User soll mittels Toasts über Erfolg/Misserfolg informiert werden.
     */
    protected void onPostExecute(Device device){
        Log.d(TAG, "onPostExecute() gestartet");
        if(device != null){
            Intent myIntent = new Intent(this.context, MainActivity.class);
            this.context.startActivity(myIntent);

            CharSequence text = "Login erfolgreich! Benutzername: " + device.getUsername() + "AndroidId: " + device.getAndroidUuid();
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
}
