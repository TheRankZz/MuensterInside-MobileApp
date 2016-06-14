package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;


/**
 * Created by Julia and Nicolas
 */
public class DeleteCommentTask extends AsyncTask<Void, Void, Integer> {

    private MuensterInsideAndroidApplication myApp;
    private Context context;
    public static final String TAG = "DeleteCommentTask";
    private int code = 1;
    private int id;

    public DeleteCommentTask(Context context, MuensterInsideAndroidApplication myApp, int id) {
        this.context = context;
        this.myApp = myApp;
        this.id = id;
        
    }

    // Im Hintergrund soll der Webservice aufgerufen werden
    @Override
    protected Integer doInBackground(Void... params) {
        Log.d(TAG, "doInBackground() gestartet");
        try {
            code = myApp.getMuensterInsideImpl().deleteComment(id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return code;
        } catch (Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Integer code) {
        Log.d(TAG, "onPostExecute() gestartet");

    }
    
}
