package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.CommentActivity;
import de.muensterinside.mobile.CommentAdapters;
import de.muensterinside.mobile.ListViewAdapters;
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
    private ListView listView;
    private List<Comment> comments;
    //private ArrayAdapter<String> adapter;
    private String androidId;
    private String username;
    private ListViewAdapters adapter;
    private Comment comment;
    private Button button;

    public MyCommentTask(Context context, MuensterInsideAndroidApplication myApp,String androidId, String username, ListView listView, Button button)

    {
        this.myApp = myApp;
        this.listView = listView;
        this.context = context;
        this.androidId = androidId;
        this.username = username;
        this.button = button;
    }

    @Override
    protected List<Comment> doInBackground(Void... params)
    {
        Log.d(TAG, "doInBackground() gestartet" );

        Device device;
        try{
            device = myApp.getMuensterInsideImpl().register(androidId,username);
            comments = myApp.getMuensterInsideImpl().getMyComments(device.getId());
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

        //List myList = new ArrayList<String>();
        //for(int i=0; i < comments.size(); i++){
          //  myList.add(comments.get(i).getText());
        //}

        //ArrayAdapter<String> adapter;
        //adapter = new ArrayAdapter<String>(context, R.layout.content_item_list_category, myList);



        //listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i <comments.size(); i++) {

            comment = comments.get(i);
            list.add(comment.getText());


        }

        //instantiate custom adapter
        CommentAdapters adapter = new CommentAdapters(list, context, myApp);

        //handle listview and assign adapter

        listView.setAdapter(adapter);



}
    }
