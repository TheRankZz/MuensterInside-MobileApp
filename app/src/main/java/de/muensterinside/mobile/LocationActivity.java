package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.DownVoteTask;
import de.muensterinside.mobile.tasks.LocationCommentTask;
import de.muensterinside.mobile.tasks.LocationTask;
import de.muensterinside.mobile.tasks.LoginTask;
import de.muensterinside.mobile.tasks.ShowCommentTask;
import de.muensterinside.mobile.tasks.UpVoteTask;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class LocationActivity extends AppCompatActivity {
    private int loc_id;
    private int cat_id;
    private Context context;
    private Device device;
    private Location location;
    private List<Comment> comments;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "LocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_location);

        // Die von der CategoryActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();

        // Ein Application Objekt wird erzeugt
        myApp = (MuensterInsideAndroidApplication) getApplication();

        SharedPreferences bool = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);

        context = this;

        if(bool.getBoolean("newCommentBool", false)==true){
            // Wenn ein neuer Kommentar erstellt wurde, wird die gleiche Location/Category angezeigt
            loc_id = bool.getInt("loc_id", 0);
            cat_id = bool.getInt("cat_id", 0);
        }
        else {
            // Sonst wird die Location angezeigt, die in der MainActivity ausgewählt wurde
            loc_id = intent.getIntExtra("loc_id", 0);
            cat_id = myCatIdPref.getInt("catId", 0);
        }

        // TextView für die Darstellung des Namens wird erzeugt
        TextView exampleName = (TextView) findViewById(R.id.textViewExampleName);

        // TextView für die Darstellung des VoteValues wird erzeugt
        final TextView exampleVote = (TextView) findViewById(R.id.textViewExampleVote);

        // TextView für die Darstellung des Links wird erzeugt
        TextView exampleLink = (TextView) findViewById(R.id.textViewExampleLink);

        // ListView für die Darstellung der 3 neusten Kommentare wird erzeugt
        ListView kommentare = (ListView) findViewById(R.id.smallCommentList);

        // Button für wird erzeugt
        Button writeComment = (Button) findViewById(R.id.button1);

        // Button für den Upvote wird erzeugt
        Button up = (Button) findViewById(R.id.up);

        // Button für den Downvote wird erzeugt
        Button down = (Button) findViewById(R.id.down);

        // Button zum anzeigen der Kommentare wird erzeugt
        Button showComment = (Button) findViewById(R.id.KommentarAnzeigen);


        // LocationTask wird aufgerufen
        LocationTask locationTask = new LocationTask(this, myApp, loc_id);
        locationTask.execute();

        // ShowCommentTask wird aufgerufen
        ShowCommentTask showCommentTask = new ShowCommentTask(this, myApp, loc_id);
        showCommentTask.execute();

        try{
            location = locationTask.get();
            comments = showCommentTask.get();
            device = myApp.getDevice();
        }
        catch (Exception e){
            location = null;
            comments = null;
            device = null;
            e.printStackTrace();
        }

        if(comments == null){
            CharSequence text = "Keine Kommentare vorhanden.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }
        else {
            List myList =  new ArrayList<String>();

            int size;
            if(comments == null){
                size = 0;
            }
            else {
                size = comments.size();
            }

            switch (size) {
                case 0: break;

                case 1: myList.add(comments.get(comments.size()-1).getText());
                    break;

                case 2: myList.add(comments.get(comments.size()-1).getText());
                    myList.add(comments.get(comments.size()-2).getText());
                    break;

                case 3: myList.add(comments.get(comments.size()-1).getText());
                    myList.add(comments.get(comments.size()-2).getText());
                    myList.add(comments.get(comments.size()-3).getText());
                    break;

                default: myList.add(comments.get(comments.size()-1).getText());
                    myList.add(comments.get(comments.size()-2).getText());
                    myList.add(comments.get(comments.size()-3).getText());
                    break;
            }

            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);

            kommentare.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        if(device == null) {
            Log.e(TAG, "Device Objekt nicht gefunden.");
        }

        if(location == null){
            Log.e(TAG, "Location Objekt nicht gefunden.");
        }

        final Location l = location;

        String voteString = String.valueOf(location.getVoteValue());

        // TextView für den Namen der Location wird befüllt
        exampleName.setText(location.getName());

        // TextView für den Namen der Location wird befüllt
        exampleVote.setText(voteString);

        // TextView für den Namen der Location wird befüllt
        exampleLink.setText(location.getDescription());

        // führt zur CommentActivity, wenn der Button gedrückt wird
        writeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "b.onClick() gestartet");
                Intent myIntent = new Intent(context, CommentActivity.class);
                myIntent.putExtra("selected", cat_id);
                myIntent.putExtra ("locId", loc_id);
                startActivity(myIntent);
            }
        });

        showComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "c.onClick() gestartet");
                Intent myIntent = new Intent(context, ShowCommentActivity.class);
                myIntent.putExtra("selected", loc_id);
                startActivity(myIntent);
            }
        });

        // Es wird ein Upvote durchgeführt
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "up.onClick() gestartet");
                UpVoteTask upVoteTask = new UpVoteTask(context, myApp, loc_id, device.getId(), exampleVote);
                upVoteTask.execute();
            }
        });

        // Es wird ein Downvote durchgeführt
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "down.onClick() gestartet");
                DownVoteTask downVoteTask = new DownVoteTask(context, myApp, loc_id, device.getId(), exampleVote);
                downVoteTask.execute();
            }
        });
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
        else if(item.getItemId() == R.id.home_button) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
