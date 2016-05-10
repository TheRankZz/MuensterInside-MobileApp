package de.muensterinside.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);
        final MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();


        final ListView listView = (ListView) findViewById(R.id.listView);
        List myList = new ArrayList<String>();
        for(int i=0; i < myApp.getCategoryService().getCategories().size(); i++){
            myList.add(myApp.getCategoryService().getCategories().get(i).getName());
        }
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
                myIntent.setClassName(getPackageName(), getPackageName() + ".CategoryActivity");
                myIntent.putExtra("selected", listView.getAdapter().getItem(arg2).toString());
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