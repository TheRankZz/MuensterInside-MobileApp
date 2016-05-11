package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Intent intent = getIntent();


        final MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();
        int cat_id = intent.getIntExtra("selected", 0);

        // generate a ListView to show the categories
        final ListView listView = (ListView) findViewById(R.id.listView);


        // fill in the test data
        List myList = new ArrayList<String>();
        final List<Location> locations = myApp.getLocationService().getAllLocation();
        for(int i=0; i < locations.size(); i++){
            Location l = locations.get(i);
            Category c = l.getCategory();
            if(c.getId() == cat_id){
                myList.add(l.getName());
            }
        }
        // the adapter get the ListView from our Layout
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /** when you click on an item you start a new activity
         * the LocationActivity gets the information on wich item you have clicked
         * */
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

}
