package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @auhtor Julia Bracht, Nicolas Burchert
 */
public class UpVoteTask extends AsyncTask<String, Integer, Integer> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "UpVoteTask";
    private int loc_id;
    private int deviceId;

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp der Zustand der Application
     * @param loc_id die Identifikation der Location
     * @param deviceId die Identifikation der Device
     */
    public UpVoteTask(Context context, MuensterInsideAndroidApplication myApp, int loc_id, int deviceId){
        this.context = context;
        this.myApp = myApp;
        this.loc_id = loc_id;
        this.deviceId = deviceId;
    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    protected Integer doInBackground(String... params){
        int result;
        try{
            result = this.myApp.getMuensterInsideImpl().upVote(loc_id, deviceId);
        }
        catch(Exception e){
            result = 2;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onPostExecute(Integer result){

    }
}
