package de.muensterinside.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.adapter.MyCommentListViewAdapters;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.MyCommentTask;
import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;
/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public class MyCommentActivity extends AppCompatActivity {

    public static final String TAG = "MyCommentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);

        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);
        MyCommentListViewAdapters adapter;
		Button button = (Button) findViewById(R.id.delete_btn);
        MyCommentTask myCommentTask = new MyCommentTask(this, myApp);
        myCommentTask.execute();

        List<Comment> comments;
        try{
            comments = myCommentTask.get();
        }
        catch (Exception e){
            comments = null;
            e.printStackTrace();
        }

        if(comments == null){
            Log.e(TAG, "Keine Liste mit Kommentaren gefunden.");
        }

        Comment comment;
        ArrayList<HashMap<String, String>> list;
        list = new ArrayList<HashMap<String,String>>();
        for(int i=0; i < comments.size(); i++){
            comment = comments.get(i);
            HashMap<String,String> temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, comment.getText());
            temp.put(SECOND_COLUMN, comment.getDate());
            list.add(temp);
        }

        // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
        adapter = new MyCommentListViewAdapters(this, list, comments, myApp);

        listView.setAdapter(adapter);

    }
}
