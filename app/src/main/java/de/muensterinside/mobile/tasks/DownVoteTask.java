package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;


import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class DownVoteTask extends AsyncTask<String, Integer, Integer> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "DownVoteTask";
    private int loc_id;
    private int deviceId;

    public DownVoteTask(Context context, MuensterInsideAndroidApplication myApp, int loc_id, int deviceId){
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

            //Gibt aus, ob DownVote erfolgreich war (0 = erfolgreich, 1/2 = nicht erfolgreich)
            result = this.myApp.getMuensterInsideImpl().downVote(this.loc_id, this.deviceId);
        }
        catch(Exception e){
            result = 2;
            e.printStackTrace();
        }
        return result;
    }

}
