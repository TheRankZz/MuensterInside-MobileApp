package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Location;
/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class LocationActivity extends AppCompatActivity {
    MuensterInsideAndroidApplication myApp;
    Location l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_location);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();
        final String androidId = intent.getStringExtra("androidId");
        myApp = (MuensterInsideAndroidApplication) getApplication();


        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Location zugegriffen,
         * damit wir auch nur die ausgewählte Location laden.
         */



       final int loc_id = intent.getIntExtra("selected", 0);
        try{
            l = myApp.getMuensterInsideMobile().getLocation(loc_id);
            String voteString = String.valueOf(l.getVoteValue());

            // TextView für den Namen der Location wird erstellt und befüllt
            TextView exampleName = (TextView) findViewById(R.id.textViewExampleName);
            exampleName.setText(l.getName());

            // TextView für den Namen der Location wird erstellt und befüllt
            final TextView exampleVote = (TextView) findViewById(R.id.textViewExampleVote);
            exampleVote.setText(voteString);

            // TextView für den Namen der Location wird erstellt und befüllt
            TextView exampleDescription = (TextView) findViewById(R.id.textViewExampleDescription);
            exampleDescription.setText(l.getDescription());


            // führt zur CommentActivity, wenn der Button gedrückt wird
            Button b = (Button) findViewById(R.id.button1);

            b.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(LocationActivity.this, CommentActivity.class);
                    myIntent.setClassName(getPackageName(), getPackageName() + ".ShowCommentActivity");
                    myIntent.putExtra("selected", loc_id);
                    myIntent.putExtra("deviceId", androidId);
                    startActivity(myIntent);
                }
            });

            Button up = (Button) findViewById(R.id.up);
            Button down = (Button) findViewById(R.id.down);
            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
                    int oldVote = l.getVoteValue();
                    oldVote = oldVote - 1;
                    l.setVoteValue(oldVote);
                    String voteString = String.valueOf(oldVote);
                    exampleVote.setText(voteString);

                }
            });

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }





}
