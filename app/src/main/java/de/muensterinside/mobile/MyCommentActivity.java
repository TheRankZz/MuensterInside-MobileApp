package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.MyCommentTask;
import de.muensterinside.mobile.tasks.ShowCommentTask;

public class MyCommentActivity extends AppCompatActivity {

    private MuensterInsideAndroidApplication myApp;
    private List<Comment> comments;
    private ListView listView;
    private int deviceId;
    public static final String TAG = "MyCommentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);


        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        ListView listView = (ListView) findViewById(R.id.liste);
        Button button = (Button) findViewById(R.id.delete_btn);

        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final int cat_id = myCatIdPref.getInt("catId", 1);
        final String androidId = sharedPreferences.getString("androidId", "Default");
        final String username = sharedPreferences.getString("username", "Default");

        MyCommentTask myCommentTask = new MyCommentTask(this, myApp, androidId, username, listView, button);
        myCommentTask.execute();
    }
}
