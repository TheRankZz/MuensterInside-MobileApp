package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Comment;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class MyCommentTask extends AsyncTask<Void,Void, List<Comment>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "MyCommentTask";
    private int device_id;
    private List<Comment> comments;

    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp repräsentiert den Zustand der Application
     */
    public MyCommentTask(Context context, MuensterInsideAndroidApplication myApp, int device_id)

    {
        this.myApp = myApp;
        this.context = context;
        this.device_id = device_id;
    }

    // Im Hintergrund wird der Webservice aufgerufen
    @Override
    protected List<Comment> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        try{
            comments = myApp.getMuensterInsideImpl().getMyComments(device_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return comments;
        }
        catch(Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }

        return null;
    }


}
