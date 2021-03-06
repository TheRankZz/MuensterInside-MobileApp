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
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.adapter.CommentListViewAdapters;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.ShowCommentTask;
import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public class ShowCommentActivity extends AppCompatActivity {

    private MuensterInsideAndroidApplication myApp;
    private List<Comment> comments;
    private int loc_id;
    private CommentListViewAdapters adapter;
    public static final String TAG = "ShowCommentActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_show_comment);


        Intent intent = getIntent();
        loc_id = intent.getIntExtra("selected", 0);


        /* In der SharedPreference wird die vorher ausgewählte
         * Id des Kommentars gespeichert.
         */
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyLocIdPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("locId", loc_id);
        editor.commit();


        //repräsentiert den übergreifenden Zustand einer App
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        //ListView zum speichern der Kommentare wird erzeugt
        ListView listView = (ListView) findViewById(R.id.commentList);


        //Netz erreichbar vorhanden ? Konnektivität wird geprüft.
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            //ShowCommentTask wird aufgerufen, um alle Kommentare der Location abzrufen
            ShowCommentTask showCommentTask = new ShowCommentTask(this, myApp, loc_id);
            showCommentTask.execute();

            //Speichert die in der TaskKlasse aufgerufenen Kommentare in eine Variable/Liste
            try {
                comments = showCommentTask.get();
            } catch (Exception e) {
                comments = null;
                e.printStackTrace();
            }

            //Wenn keine Kommentare vorhanden sinid
            if (comments == null) {
                CharSequence text = "Keine Kommentare vorhanden.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            } else {

                //Wenn KOmmentare vorhanden sind, werden sie in einer Liste gespeichert
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

                // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
                adapter = new CommentListViewAdapters(this, list);

                listView.setAdapter(adapter);
            }
        }
        else{

            //Ist keine Verbindung vorhanden, passiert nichts und der Toast wird ausgegeben (Verbindung fehlgeschlagen)
            Log.d(TAG, "Keine Internetverbindung");
            Toast.makeText(ShowCommentActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        //Hier füllen (inflate) wir das Options Menu mit dem Menüeintrag,
        // den wir in der XML-Datei menu_menu_main.xml definiert haben.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() gestartet");
        ///Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf.
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        // /Wenn "Home" gedrückt wurde, rufen wir die MainActivity auf
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
