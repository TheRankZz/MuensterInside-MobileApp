package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;
/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_category);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();
        final MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Category zugegriffen,
         * damit wir auch nur die Locations laden,
         * die zu der ausgewählten Category gehören.
         */
        int cat_id = intent.getIntExtra("selected", 0);

        // ListView wird erstellt um Daten anzeigen zu können und bekommt ein Layout
        final ListView listView = (ListView) findViewById(R.id.listView);


        /**
         * Logik zum befüllen der "richtigen" Locations
         */
        List myList = new ArrayList<String>();
        final List<Location> locations = myApp.getLocationService().getAllLocation();
        for(int i=0; i < locations.size(); i++){
            Location l = locations.get(i);
            Category c = l.getCategory();
            if(c.getId() == cat_id){
                myList.add(l.getName());
            }
        }
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /**
         * Wenn ein Eintrag aus der listView ausgewählt wird,
         * soll die CategoryActivity gestartet werden.
         * Zusätzlich soll die Id, von der Location die ausgewählt wurde,
         * an die LocationActivity mit übergeben werden.
         */
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                Intent myIntent = new Intent(CategoryActivity.this, LocationActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".LocationActivity");
                myIntent.putExtra("selected", arg2);
                startActivity(myIntent);

            }
        });



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
