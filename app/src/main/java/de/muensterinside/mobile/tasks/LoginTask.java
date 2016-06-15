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
 * @author Julia Bracht, Nicolas Burchert
 */
public class LoginTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private String android_id;
    public final static String TAG = "LoginTask";

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp repräsentiert den Zustand der Application
     * @param android_id die eindeutige Identifizität des Gerätes (String)
     */
    public LoginTask(Context context, MuensterInsideAndroidApplication myApp, String android_id){
        this.context = context;
        this.myApp = myApp;
        this.android_id = android_id;
    }

    // Im Hintergrund wird der Webservice aufgerufen
    @Override
    protected Device doInBackground(String... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            // Die Login Methode liefert anhand der Android Device-ID ein Device Objekt
            Device device = myApp.getMuensterInsideImpl().login(this.android_id);
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


}
