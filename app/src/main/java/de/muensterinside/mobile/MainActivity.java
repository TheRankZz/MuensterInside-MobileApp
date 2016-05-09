package de.muensterinside.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);
        final MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();


        // Get the categories dynamically
        Button button_essen = (Button) findViewById(R.id.button_essen);
        button_essen.setText(myApp.getCategoryService().getCategory(0).getName());
        Button button_party = (Button) findViewById(R.id.button_party);
        button_party.setText(myApp.getCategoryService().getCategory(1).getName());
        Button button_sehenswuerdigkeiten = (Button) findViewById(R.id.button_sehenswuerdigkeiten);
        button_sehenswuerdigkeiten.setText(myApp.getCategoryService().getCategory(2).getName());
        Button button_hotel = (Button) findViewById(R.id.button_hotel);
        button_hotel.setText(myApp.getCategoryService().getCategory(3).getName());
        Button button_shopping = (Button) findViewById(R.id.button_shopping);
        button_shopping.setText(myApp.getCategoryService().getCategory(4).getName());
        Button button_veranstaltungen = (Button) findViewById(R.id.button_veranstaltungen);
        button_veranstaltungen.setText(myApp.getCategoryService().getCategory(5).getName());
        // Capture button_essen clicks
        button_essen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(myIntent);
            }
        });
        // Capture button_party clicks
        button_party.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(myIntent);
            }
        });
        // Capture button_hotel clicks
        button_hotel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(myIntent);
            }
        });
        // Capture button_sehenswuerdigkeiten clicks
        button_sehenswuerdigkeiten.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(myIntent);
            }
        });
        // Capture button_shopping clicks
        button_shopping.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(myIntent);
            }
        });
        // Capture button_veranstaltungen clicks
        button_veranstaltungen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(myIntent);
            }
        });


    }
}