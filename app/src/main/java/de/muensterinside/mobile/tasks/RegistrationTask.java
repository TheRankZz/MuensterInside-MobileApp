package de.muensterinside.mobile.tasks;

import android.content.Context;

import android.os.AsyncTask;
import android.util.Log;



import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class RegistrationTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private String android_id;
    private String username;
    public static final String TAG = "RegistrationTask";

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp der Zustand der Application
     * @param android_id die eindeutige Identifizitaet des Gerätes
     * @param username der Name des Nutzers
     */
    public RegistrationTask(Context context, MuensterInsideAndroidApplication myApp,
                            String android_id, String username){
        this.context = context;
        this.myApp = myApp;
        this.android_id = android_id;
        this.username = username;
    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    protected Device doInBackground(String... params) {
        Log.d(TAG, "doInBackground() gestartet");


            try {
                //Gibt anhand der Android Device_ID und des Usernames ein Device_Objekt zurück
                Device device = this.myApp.getMuensterInsideImpl().register(android_id, username);
                this.myApp.setDevice(device);
                Log.i(TAG, "doInBackground() erfolgreich");
                return device;

            } catch (Exception e) {
                Log.e(TAG, "doInBackground() fehlgeschlagen");
                e.printStackTrace();
            }
            return null;
        }



    }

