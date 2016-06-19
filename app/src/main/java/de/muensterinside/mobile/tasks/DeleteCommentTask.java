package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;



import de.muensterinside.mobile.MuensterInsideAndroidApplication;


/**
 * Created by Julia and Nicolas
 * @author Julia Bracht, Nicolas Burchert
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
            //Gibt aus, ob Löschen des Kommentars erfolgreich (0 = erfolgreich, 1 = nicht erfolgreich)
            code = myApp.getMuensterInsideImpl().deleteComment(id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return code;
        } catch (Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    
}
