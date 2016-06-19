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

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class LocationCommentTask extends AsyncTask<Void, Void ,List <Comment>> {
    private MuensterInsideAndroidApplication myApp;
    private List<Comment> comments;
    private ListView listView;
    private final int loc_id;
    private Context context;
    private static final String TAG = "LocationCommentTask";

    public LocationCommentTask(Context context, int loc_id, MuensterInsideAndroidApplication myApp, ListView listView)

    {
        this.context = context;
        this.loc_id = loc_id;
        this.myApp = myApp;
        this.listView = listView;

    }

    // Im Hintergrund wird der Webservice aufgerufen.
    @Override
    public List<Comment> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet");
        try {
            //Gibt eine Liste alle Kommentare der Location zurück
            this.comments = myApp.getMuensterInsideImpl().getCommentsByLocation(loc_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return comments;

        }
        catch(Exception e)
        {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onPostExecute(List <Comment> comments)
    {
        Log.d(TAG, "onPostExecute() gestartet");

        List myList =  new ArrayList<String>();
        for(int i = comments.size(); i > comments.size()-3; i++ )
        {
            myList.add(comments.get(i).getText());
        }

        // Es wird ein Adapter erstellt, der die ListView mit Einträgen befüllt
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(context, R.layout.activity_location);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}
