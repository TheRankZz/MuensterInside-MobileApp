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
    private String android_id;
    private String username;
    public static final String TAG = "RegistrationTask";

    /* Der Konstruktor erwartet ein Context Objekt
     * und ein Application Objekt.
     */
    public RegistrationTask(Context context, MuensterInsideAndroidApplication myApp,
                            String android_id, String username){
        this.context = context;
        this.myApp = myApp;
        this.android_id = android_id;
        this.username = username;
    }

    // Im Hintergrund soll der Webservice aufgerufen werden.
    @Override
    protected Device doInBackground(String... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            /* Die register Methode liefert anhand der Android Device-ID
             * und des Usernames ein Device Objekt.
             */
            Device device = this.myApp.getMuensterInsideImpl().register(android_id, username);
            this.myApp.setDevice(device);
            Log.i(TAG, "doInBackground() erfolgreich");
            return device;

        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Device device){
        Log.d(TAG, "onPostExecute() gestartet");

    }
}
