package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Comment;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @auhtor Julia Bracht, Nicolas Burchert
 */
public class ShowCommentTask extends AsyncTask<Void, Void, List<Comment>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "ShowCommentTask";
    private final int loc_id;
    private List<Comment> comments;


    /**
     * Konstruktor
     * @param context der Inhalt der Activity
     * @param myApp der Zustand der Application
     * @param loc_id die Identifikation der Location
     */
    public ShowCommentTask(Context context, MuensterInsideAndroidApplication myApp,int loc_id)
    {
        this.loc_id = loc_id;
        this.myApp = myApp;
        this.context = context;


    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    protected List<Comment> doInBackground(Void... params){
        Log.d(TAG, "doInBackground() gestartet" );
        try{
            comments = myApp.getMuensterInsideImpl().getCommentsByLocation(this.loc_id);
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
