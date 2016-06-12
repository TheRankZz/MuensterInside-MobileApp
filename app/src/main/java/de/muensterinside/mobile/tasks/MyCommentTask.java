package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class MyCommentTask extends AsyncTask<Void,Void, List<Comment>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "MyCommentTask";
    private int device_id;
    private List<Comment> comments;

    public MyCommentTask(Context context, MuensterInsideAndroidApplication myApp)

    {
        this.myApp = myApp;
        this.context = context;
    }

    @Override
    protected List<Comment> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        try{
            device_id = this.myApp.getDevice().getId();
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

    @Override
    protected void onPostExecute(List<Comment> comments)
    {
        Log.d(TAG, "onPostExecute() gestartet");
    }

}
