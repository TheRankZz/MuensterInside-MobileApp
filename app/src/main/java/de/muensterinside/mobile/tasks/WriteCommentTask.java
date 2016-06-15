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
public class WriteCommentTask extends AsyncTask<Void, Void, Integer> {
    private MuensterInsideAndroidApplication myApp;
    private Context context;
    private Device device;
    private String text;
    private int loc_id;
    private int code = 1;
    public static final String TAG = "WriteCommentTask";

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp der Zustand der Application
     * @param text der Text des Kommentars
     * @param loc_id die Identifikation der Location
     */
    public WriteCommentTask(Context context, MuensterInsideAndroidApplication myApp,
                            String text, int loc_id) {
        this.context = context;
        this.myApp = myApp;
        this.text = text;
        this.loc_id = loc_id;
    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    protected Integer doInBackground(Void... params) {
        Log.d(TAG, "doInBackground() gestartet");
        try {
            this.device = myApp.getDevice();
            code = myApp.getMuensterInsideImpl().saveComment(this.text,this.device.getId(),
                    this.loc_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return code;
        } catch (Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return code;
    }



}
