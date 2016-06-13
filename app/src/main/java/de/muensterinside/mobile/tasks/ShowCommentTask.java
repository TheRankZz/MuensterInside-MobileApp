package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.CommentAdapters;
import de.muensterinside.mobile.adapter.ListViewAdapters;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Comment;

import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class ShowCommentTask extends AsyncTask<Void, Void, List<Comment>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "ShowCommentTask";
    private final int loc_id;
    private List<Comment> comments;



    public ShowCommentTask(Context context, MuensterInsideAndroidApplication myApp,int loc_id)
    {
        this.loc_id = loc_id;
        this.myApp = myApp;
        this.context = context;


    }

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

    @Override
    protected void onPostExecute(List<Comment> comments)
    {
        Log.d(TAG, "onPostExecute() gestartet");



    }

}
