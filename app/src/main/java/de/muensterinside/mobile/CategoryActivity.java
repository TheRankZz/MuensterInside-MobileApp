package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
    int cat_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der CategoryActivity ausgewählt
        setContentView(R.layout.activity_category);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();

        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Category zugegriffen,
         * damit wir auch nur die Locations laden,
         * die zu der ausgewählten Category gehören.
         */
        cat_id = intent.getIntExtra("selected", 0);

        LocationListTask locationListTask = new LocationListTask(this, cat_id);
        locationListTask.execute();
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


    private class LocationListTask extends AsyncTask<Integer, Void, List<Location>> {
        private MuensterInsideAndroidApplication myApp;
        private List<Location> locations;
        private Context context;
        private int cat_id;
        private Location l;
        private Category c;




        public LocationListTask(Context context, int cat_id){
            this.context = context;
            this.cat_id = cat_id;
        }

        @Override
        protected List<Location> doInBackground(Integer... params){
            try {
                this.myApp = (MuensterInsideAndroidApplication) getApplication();
                this.locations = myApp.getMuensterInsideMobile().getLocationsByCategory(this.cat_id);
                return this.locations;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Location> locations){
            ArrayList<HashMap<String, String>> list;
            list = new ArrayList<HashMap<String,String>>();
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

            // ListView wird erstellt um Daten anzeigen zu können und bekommt ein Layout
            ListView listView = (ListView) findViewById(R.id.listView);

            // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
            ListViewAdapters adapter = new ListViewAdapters(context, list);
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
    }

}
