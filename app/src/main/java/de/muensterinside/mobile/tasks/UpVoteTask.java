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
    private TextView exampleVote;
    private int cat_id;
    private Location location;
    private List<Location> locations;

    public UpVoteTask(Context context, MuensterInsideAndroidApplication myApp, int loc_id, int deviceId, TextView exampleVote, int cat_id){
        this.context = context;
        this.myApp = myApp;
        this.loc_id = loc_id;
        this.deviceId = deviceId;
        this.exampleVote = exampleVote;
        this.cat_id = cat_id;
    }

    @Override
    protected Integer doInBackground(String... params){
        try{
            boolean isVoted = this.myApp.getMuensterInsideImpl().isVoted(this.loc_id, deviceId);
            this.locations = this.myApp.getMuensterInsideImpl().getLocationsByCategory(this.cat_id);
            this.location = this.locations.get(this.loc_id);
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
        if(result != null && result == 0){
            CharSequence text = "UpVote erfolgreich";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.i(TAG, "UpVote erfolgreich");
        }
        else if(result == 2) {
            CharSequence text = "Es gab schon ein Vote";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.i(TAG, "Es gab schon ein Vote");
        }
        else {
            CharSequence text = "UpVote nicht erfolgreich";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.i(TAG, "UpVote nicht erfolgreich");
        }
        int voteValue = location.getVoteValue();
        String voteString = String.valueOf(voteValue);
        exampleVote.setText(voteString);
    }
}
