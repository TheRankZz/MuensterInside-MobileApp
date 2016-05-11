package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Intent intent = getIntent();


        final MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        String name = "Hallo";
        int vote = 21;
        String description = "sehr gut";

        List<Location> locations = myApp.getLocationService().getAllLocation();

        int loc_id = intent.getIntExtra("selected", 0);
        Location l = locations.get(loc_id);


        String voteString = String.valueOf(l.getVoteValue());
        TextView exampleName = (TextView) findViewById(R.id.textViewExampleName);
        exampleName.setText(l.getName());

        TextView exampleVote = (TextView) findViewById(R.id.textViewExampleVote);
        exampleVote.setText(voteString);

        TextView exampleDescription = (TextView) findViewById(R.id.textViewExampleDescription);
        exampleDescription.setText(l.getDescription());
    }



}
