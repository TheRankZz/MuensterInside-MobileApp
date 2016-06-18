package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

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
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_my_comment);

        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        ListView listView = (ListView) findViewById(R.id.liste);

        MyCommentListViewAdapters adapter;

        Button button = (Button) findViewById(R.id.delete_btn);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int device_id = sharedPreferences.getInt("deviceId", 0);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            MyCommentTask myCommentTask = new MyCommentTask(this, myApp, device_id);
            myCommentTask.execute();

            List<Comment> comments;
            try {
                comments = myCommentTask.get();
            }
            catch (Exception e) {
                comments = null;
                e.printStackTrace();
            }

            if (comments == null) {
                CharSequence text = "Bisher keine Kommentare angelegt.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
                Log.e(TAG, "Keine Liste mit Kommentaren gefunden.");
            }
            else {
                Comment comment;
                ArrayList<HashMap<String, String>> list;
                list = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < comments.size(); i++) {
                    comment = comments.get(i);
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put(FIRST_COLUMN, comment.getText());
                    temp.put(SECOND_COLUMN, comment.getDate());
                    list.add(temp);
                }

                // Es wird ein Adapter erstellt der die listView mit Einträgen befüllt
                adapter = new MyCommentListViewAdapters(this, list, comments, myApp);

                listView.setAdapter(adapter);
            }
        }
        else {
            Log.e(TAG, "Keine Internetverbindung");
            Toast.makeText(MyCommentActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() gestartet");
        //Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        // Home Button
        else if(item.getItemId() == R.id.action_home){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
