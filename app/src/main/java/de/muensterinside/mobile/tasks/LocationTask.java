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
    private final int loc_id;
    private int cat_id;
    private TextView exampleName;
    private TextView exampleVote;
    private TextView exampleDescription;
    private ListView kommentare;
    private Button b;
    private Button up;
    private Button down;
    private Button c;
    public static final String TAG = "LocationTask";


    public LocationTask(Context context, int loc_id, int cat_id,
                        MuensterInsideAndroidApplication myApp, TextView exampleName,
                        TextView exampleVote, ListView kommentare, TextView exampleDescription, Button b,
                        Button up, Button down, Button c){
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

    }

    @Override
    protected Location doInBackground(Integer... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            this.locations = myApp.getMuensterInsideMobile().getLocationsByCategory(this.cat_id);
            this.comments = myApp.getMuensterInsideMobile().getCommentsByLocation(this.loc_id);
            this.location = this.locations.get(this.loc_id);
            Log.i(TAG, "doInBackground() erfolgreich");
            return this.location;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onPostExecute(Location location){
        Log.d(TAG, "onPostExecute() gestartet");
        final Location l = location;
        String voteString = String.valueOf(location.getVoteValue());

        // TextView für den Namen der Location wird befüllt
        exampleName.setText(location.getName());

        // TextView für den Namen der Location wird befüllt
        exampleVote.setText(voteString);

        // TextView für den Namen der Location wird befüllt
        exampleDescription.setText(location.getDescription());


        List myList =  new ArrayList<String>();


       int laenge = comments.size()-1;

        for(int i = laenge; i >= laenge-2; i--) {

            myList.add(comments.get(i).getText());
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

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "up.onClick() gestartet");
                int oldVote = l.getVoteValue();
                oldVote = oldVote +1;
                l.setVoteValue(oldVote);
                String voteString = String.valueOf(oldVote);
                exampleVote.setText(voteString);

            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "down.onClick() gestartet");
                int oldVote = l.getVoteValue();
                oldVote = oldVote - 1;
                l.setVoteValue(oldVote);
                String voteString = String.valueOf(oldVote);
                exampleVote.setText(voteString);

            }
        });
    }
}
