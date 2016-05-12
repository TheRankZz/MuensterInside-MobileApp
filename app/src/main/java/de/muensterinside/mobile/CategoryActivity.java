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
import android.widget.Toast;
import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Location;

import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class CategoryActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;
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

        list = new ArrayList<HashMap<String,String>>();

        Location l = null;
        Category c = null;
        /**
         * Logik zum befüllen der "richtigen" Locations
         */
        final List<Location> locations = myApp.getMuensterInsideMobile().getLocationsByCategory(cat_id);
        for(int i=0; i < locations.size(); i++){
            l = locations.get(i);
            c = l.getCategory();
            if(c.getId() == cat_id){
                HashMap<String,String> temp = new HashMap<String, String>();
                temp.put(FIRST_COLUMN, l.getName());
                temp.put(SECOND_COLUMN, String.valueOf(l.getVoteValue()));
                list.add(temp);

            }
        }

        ListViewAdapters adapter = new ListViewAdapters(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                Intent myIntent = new Intent(CategoryActivity.this, LocationActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".LocationActivity");
                myIntent.putExtra("selected", position);
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
