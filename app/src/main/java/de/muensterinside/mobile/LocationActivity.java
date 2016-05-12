package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;
/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_location);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();
        final MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        List<Location> locations = myApp.getLocationService().getAllLocation();
        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Location zugegriffen,
         * damit wir auch nur die ausgewählte Location laden.
         */
        int loc_id = intent.getIntExtra("selected", 0);
        Location l = locations.get(loc_id);
        String voteString = String.valueOf(l.getVoteValue());

        // TextView für den Namen der Location wird erstellt und befüllt
        TextView exampleName = (TextView) findViewById(R.id.textViewExampleName);
        exampleName.setText(l.getName());

        // TextView für den Namen der Location wird erstellt und befüllt
        TextView exampleVote = (TextView) findViewById(R.id.textViewExampleVote);
        exampleVote.setText(voteString);

        // TextView für den Namen der Location wird erstellt und befüllt
        TextView exampleDescription = (TextView) findViewById(R.id.textViewExampleDescription);
        exampleDescription.setText(l.getDescription());
    }



}
