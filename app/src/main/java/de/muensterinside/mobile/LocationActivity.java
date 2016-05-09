package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Intent intent = getIntent();
        // shows the selected button from KategorieActivity
        ((TextView)(findViewById(R.id.textView1))).setText("Es wurde " + intent.getStringExtra("selected") + " gewählt!");
    }

}
