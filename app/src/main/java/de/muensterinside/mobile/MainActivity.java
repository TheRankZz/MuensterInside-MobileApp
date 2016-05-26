package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;
import static de.muensterinside.mobile.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import de.muensterinside.mobile.entities.Category;
/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hier wird das Aussehen der MainActivity festgelegt
        setContentView(R.layout.activity_main);

        CategoryTask categoryTask = new CategoryTask(this);
        categoryTask.execute();
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

    private class CategoryTask extends AsyncTask<Void, Void, List<Category>> {
        private MuensterInsideAndroidApplication myApp;
        private List<Category> categories;
        private Context context;


        public CategoryTask(Context context){
            this.context = context;
        }

        @Override
        protected List<Category> doInBackground(Void... params){
            try {
                this.myApp = (MuensterInsideAndroidApplication) getApplication();
                this.categories = myApp.getMuensterInsideMobile().getCategories();
                return this.categories;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Category> categories){
            List myList = new ArrayList<String>();
            for(int i=0; i < categories.size(); i++){
                myList.add(categories.get(i).getName());
            }
            final List<Category> test = categories;
            // ListView wird erstellt um Daten anzeigen zu können und bekommt ein Layout
            final ListView listView = (ListView) findViewById(R.id.listView);

            // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
            ArrayAdapter<String> adapter;
            adapter=new ArrayAdapter<String>(context, R.layout.content_item_list_category, myList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            /**
             * Wenn ein Eintrag aus der listView ausgewählt wird,
             * soll die CategoryActivity gestartet werden.
             * Zusätzlich soll die Id, von der Category die ausgewählt wurde,
             * an die CategoryActivity mit übergeben werden.
             */
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                    Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                    myIntent.setClassName(getPackageName(), getPackageName() + ".CategoryActivity");
                    String name = listView.getAdapter().getItem(arg2).toString();
                    int id = 1;
                    for(int i=0; i < test.size(); i++){
                        if(test.get(i).getName().equals(name)){
                            id = test.get(i).getId();
                        }
                    }
                    myIntent.putExtra("selected", id);
                    startActivity(myIntent);
                }
            });
        }
    }

}