package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Comment;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class ShowCommentTask extends AsyncTask<Void, Void, List<Comment>> {

    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private static final String TAG = "ShowCommentTask";
    private final int loc_id;
    private ListView listView;
    private List<Comment> comments;
    private ArrayAdapter<String> adapter;



    public ShowCommentTask(Context context, MuensterInsideAndroidApplication myApp,int loc_id, ListView listView  )
    {
        this.loc_id = loc_id;
        this.myApp = myApp;
        this.listView = listView;
        this.context = context;

    }

    @Override
    protected List<Comment> doInBackground(Void... params){
        Log.d(TAG, "doInBackground() gestartet" );
        try{
            int temp_loc_id = this.loc_id + 1;
            comments = myApp.getMuensterInsideImpl().getCommentsByLocation(temp_loc_id);
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

        if(comments == null){
            CharSequence text = "Keine Kommentare vorhanden.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            List myList = new ArrayList<String>();
            for (int i = 0; i < comments.size(); i++) {
                myList.add(comments.get(i).getText());
            }

            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(context, R.layout.content_item_list_category, myList);

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

}
