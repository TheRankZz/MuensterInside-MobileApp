package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.ShowCommentTask;


public class ShowCommentActivity extends AppCompatActivity {

    private MuensterInsideAndroidApplication myApp;
    private List<Comment> comments;
    private ListView listView;
    private int loc_id;
    public static final String TAG = "ShowCommentActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment);


     Intent intent = getIntent();

        loc_id = intent.getIntExtra("selected", 0);


        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyLocIdPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("locId", loc_id);
        editor.commit();


        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.listView);

        ShowCommentTask showCommentTask = new ShowCommentTask(this, myApp, loc_id, listView);
        showCommentTask.execute();







    }
}
