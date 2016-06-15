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
 * @author Julia Bracht, Nicolas Burchert
 */
public class DownVoteTask extends AsyncTask<String, Integer, Integer> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "DownVoteTask";
    private int loc_id;
    private int deviceId;
    private TextView exampleVote;
    private Location location;

    public DownVoteTask(Context context, MuensterInsideAndroidApplication myApp, int loc_id, int deviceId, TextView exampleVote){
        this.context = context;
        this.myApp = myApp;
        this.loc_id = loc_id;
        this.deviceId = deviceId;
        this.exampleVote = exampleVote;
    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    protected Integer doInBackground(String... params){
        try{
            boolean isVoted = this.myApp.getMuensterInsideImpl().isVoted(this.loc_id, this.deviceId);
            this.location = this.myApp.getMuensterInsideImpl().getLocation(this.loc_id);
            if(isVoted == false){
                int result = this.myApp.getMuensterInsideImpl().downVote(this.loc_id, this.deviceId);
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



    /**
     * Hier werden Ausgaben(Toasts) für Downvotes ausgegeben, um den Nutzern zu zeigen, ob der DownVote erfolgreich war.
     * @param result gibt an, ob der DownVote erfolgreich war (0 == erfolgreich, 2 == es gab schon einen Vote)
     */
    @Override
    public void onPostExecute(Integer result){
        //Überprüft, ob DownVote erfolgreich war (Result = 0)
        if(result != null && result == 0){
            CharSequence text = "DownVote erfolgreich";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();//Toast ausgeben
            Log.i(TAG, "DownVote erfolgreich");
        }
        //Überprüft, ob es schon einen Vote gab (Result = 2)
        else if(result == 2) {
            CharSequence text = "Es gab schon ein Vote";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show(); //Toast ausgeben
            Log.i(TAG, "Es gab schon ein Vote");
        }
        //Überprüfen ob der Code nicht auf 0 gesetzt wurde und der UpVote nicht erfolgreich war
        else {
            CharSequence text = "UpVote nicht erfolgreich";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show(); //Toast ausgeben
            Log.i(TAG, "DownVote nicht erfolgreich");
        }
        int voteValue = location.getVoteValue();
        String voteString = String.valueOf(voteValue);
        exampleVote.setText(voteString);
    }
}
