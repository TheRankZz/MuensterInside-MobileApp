package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.CommentActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.ShowCommentActivity;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class LocationTask extends AsyncTask<Integer, Void, Location> {
    private MuensterInsideAndroidApplication myApp;
    private Location location;
    private List<Location> locations;
    private List<Comment>comments;
    private Context context;
    private Device device;
    private int loc_id;
    private int cat_id;
    private String deviceId;
    private String username;
    private TextView exampleName;
    private TextView exampleVote;
    private TextView exampleDescription;
    private ListView kommentare;
    private Button b;
    private Button up;
    private Button down;
    private Button c;
    public static final String TAG = "LocationTask";

    /* Konstruktor erwartet ein Context Objekt,
     * die ID der Location die ausgewählt wurde,
     * die ID der Kategorie die ausgewählt wurde,
     * ein Application Objekt und mehrere TextViews/Buttons
     * für die Darstellung der LocationActivity.
     */
    public LocationTask(Context context, int loc_id, int cat_id,
                        MuensterInsideAndroidApplication myApp, TextView exampleName,
                        TextView exampleVote, TextView exampleDescription, Button b,
                        Button up, Button down, Button c, String deviceId, String username, ListView kommentare){
        this.context = context;
        this.loc_id = loc_id;
        this.cat_id = cat_id;
        this.myApp = myApp;
        this.exampleName = exampleName;
        this.exampleVote = exampleVote;
        this.kommentare = kommentare;
        this.exampleDescription = exampleDescription;
        this.b = b;
        this.up = up;
        this.down = down;
        this.c = c;
        this.deviceId = deviceId;
        this.username = username;
    }

    // Im Hintergrund wird der Webservice aufgerufen
    @Override
    protected Location doInBackground(Integer... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            /* Die Methode getLocationsByCategory liefert anhand der ID der ausgewählten
             * Kategorie eine Liste mit Locations zurück.
             */
            this.comments = myApp.getMuensterInsideMobile().getCommentsByLocation(this.loc_id);
            this.location = myApp.getMuensterInsideMobile().getLocation(this.loc_id);

            this.device = myApp.getMuensterInsideMobile().register(this.deviceId, this.username);

            Log.i(TAG, "doInBackground() erfolgreich");
            return this.location;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    // onPostExecute Methode erwartet ein Location Objekt von der doInBackground Methode
    @Override
    public void onPostExecute(Location location){
        Log.d(TAG, "onPostExecute() gestartet");

        final Location l = location;

        String voteString = String.valueOf(l.getVoteValue());

        // TextView für den Namen der Location wird befüllt
        exampleName.setText(l.getName());

        // TextView für den Namen der Location wird befüllt
        exampleVote.setText(voteString);

        // TextView für den Namen der Location wird befüllt
        exampleDescription.setText(l.getDescription());


        List myList =  new ArrayList<String>();


        //TODO: Wird später nicht mehr rückwärts gezählt!
        switch (comments.size()) {
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
        adapter = new ArrayAdapter<String>(context, R.layout.content_item_list_category, myList);

        kommentare.setAdapter(adapter);
        adapter.notifyDataSetChanged();





        final int test = this.loc_id;
        // führt zur CommentActivity, wenn der Button gedrückt wird
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "b.onClick() gestartet");
                Intent myIntent = new Intent(context, CommentActivity.class);
                myIntent.putExtra("selected", test);
                myIntent.putExtra ("locId", loc_id);
                context.startActivity(myIntent);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "c.onClick() gestartet");
                Intent myIntent = new Intent(context, ShowCommentActivity.class);
                myIntent.putExtra("selected", loc_id);
                context.startActivity(myIntent);
            }
        });

        // Es wird ein Upvote durchgeführt
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "up.onClick() gestartet");
                UpVoteTask upVoteTask = new UpVoteTask(context, myApp, loc_id, device.getId(), exampleVote, cat_id);
                upVoteTask.execute();
                int voteValue = l.getVoteValue();
                String voteString = String.valueOf(voteValue);
                exampleVote.setText(voteString);

            }
        });

        // Es wird ein Downvote durchgeführt
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "down.onClick() gestartet");
                DownVoteTask downVoteTask = new DownVoteTask(context, myApp, loc_id, device.getId(), exampleVote, cat_id);
                downVoteTask.execute();
                int voteVote = l.getVoteValue();
                String voteString = String.valueOf(voteVote);
                exampleVote.setText(voteString);

            }
        });
    }
}
