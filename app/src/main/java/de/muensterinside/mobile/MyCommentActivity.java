package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.MyCommentTask;
import de.muensterinside.mobile.tasks.ShowCommentTask;

public class MyCommentActivity extends AppCompatActivity {

    public static final String TAG = "MyCommentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);

        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);
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
        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i <comments.size(); i++) {

            comment = comments.get(i);
            list.add(comment.getText());


        }

        //instantiate custom adapter
        CommentAdapters adapter = new CommentAdapters(list, this, myApp);

        //handle listview and assign adapter

        listView.setAdapter(adapter);

    }
}
