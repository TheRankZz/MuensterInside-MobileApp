package de.muensterinside.mobile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.adapter.SlidingMenuAdapter;
import de.muensterinside.mobile.fragments.Category1;
import de.muensterinside.mobile.fragments.Category2;
import de.muensterinside.mobile.fragments.Category3;
import de.muensterinside.mobile.fragments.Category4;
import de.muensterinside.mobile.fragments.Category5;
import de.muensterinside.mobile.fragments.Category6;
import de.muensterinside.mobile.model.ItemSlideMenu;
import de.muensterinside.mobile.tasks.CategoryTask;

/**
 * Created by Julia Bracht and Nicolas Burchert.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);

        // Hier wird das Aussehen der MainActivity festgelegt
        setContentView(R.layout.activity_main);

        SharedPreferences boolPref = getSharedPreferences("MyBoolPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = boolPref.edit();
        editor1.putBoolean("newLocationBool", false);
        editor1.commit();

        SharedPreferences boolPref1 = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = boolPref1.edit();
        editor2.putBoolean("newCommentBool", false);
        editor2.commit();

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, "Essen"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, "Party"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, "Hotel"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, "Shopping"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, "Sehenswürdigkeiten"));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, "Veranstaltungen"));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //Display icon to open/ close sliding list
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Display fragment 1 when start
        replaceFragment(0);
        //Hanlde on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set title
                setTitle(listSliding.get(position).getTitle());
                //item selected
                listViewSliding.setItemChecked(position, true);
                //Replace fragment
                replaceFragment(position);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        //Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        else if(item.getItemId() == R.id.home_button) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //Create method replace fragment

    private void replaceFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {
            case 0:
                fragment = new Category1();
                editor.putInt("catId", 0);
                editor.commit();
                break;
            case 1:
                fragment = new Category2();
                editor.putInt("catId", 1);
                editor.commit();
                break;
            case 2:
                fragment = new Category3();
                editor.putInt("catId", 2);
                editor.commit();
                break;
            case 3:
                fragment = new Category4();
                editor.putInt("catId", 3);
                editor.commit();
                break;
            case 4:
                fragment = new Category5();
                editor.putInt("catId", 4);
                editor.commit();
                break;
            case 5:
                fragment = new Category6();
                editor.putInt("catId", 5);
                editor.commit();
                break;
            default:
                fragment = new Category1();
                editor.putInt("catId", 1);
                editor.commit();
                break;
        }

        if(null!=fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}