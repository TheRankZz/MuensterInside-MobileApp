package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class UpVoteTask extends AsyncTask<String, Integer, Integer> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "UpVoteTask";
    private int loc_id;
    private int deviceId;

    public UpVoteTask(Context context, MuensterInsideAndroidApplication myApp, int loc_id, int deviceId){
        this.context = context;
        this.myApp = myApp;
        this.loc_id = loc_id;
        this.deviceId = deviceId;
    }

    @Override
    protected Integer doInBackground(String... params){
        try{
            boolean isVoted = this.myApp.getMuensterInsideImpl().isVoted(this.loc_id, deviceId);
            if(isVoted == false){
                int result = this.myApp.getMuensterInsideImpl().upVote(loc_id, deviceId);
                return result;
            }
            else {
                return 2;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute(Integer result){

    }
}
