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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.adapter.SlidingMenuAdapter;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.fragments.Category1;
import de.muensterinside.mobile.fragments.Category2;
import de.muensterinside.mobile.fragments.Category3;
import de.muensterinside.mobile.fragments.Category4;
import de.muensterinside.mobile.fragments.Category5;
import de.muensterinside.mobile.fragments.Category6;
import de.muensterinside.mobile.model.ItemSlideMenu;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.tasks.CategoryTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
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

        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_main);

        /* In der SharedPreference wird die vorher ausgewählte
         * Id der Location(und ein Boolean der auf true gesetzt wird, wenn ein neue Location erzeugt wird) gespeichert.
         */
        SharedPreferences boolPref = getSharedPreferences("MyBoolPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = boolPref.edit();
        editor1.putBoolean("newLocationBool", false);
        editor1.commit();

        /* In der SharedPreference wird die vorher ausgewählte
         * Id des Kommentars(und ein Boolean der auf true gesetzt wird, wenn ein neuer Kommentar erzeugt wird) gespeichert.
         */
        SharedPreferences boolPref1 = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = boolPref1.edit();
        editor2.putBoolean("newCommentBool", false);
        editor2.commit();


          /* In der SharedPreference wird die vorher ausgewählte
         * ID einer Kategorie  ausgelesen.
         */
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        // Kategorien werden im Hintergrund geladen
        CategoryTask categoryTask = new CategoryTask(this,myApp);
        categoryTask.execute();

        List<Category> categories;
        try{
            // Kategorien aus dem CategoryTask werden für die Weiterverwendung gespeichert
            categories = categoryTask.get();
        }
        catch (Exception e){
            categories = null;
            e.printStackTrace();
        }

        // Die Namen der Kategorien werden in eine Liste gespeichert
        List myList = new ArrayList<String>();
        for (int i = 0; i < categories.size(); i++) {
            myList.add(categories.get(i).getName());
        }

        // NavigationBar
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();

        //Fügt Items in die NavigationBar ein
        for(int i=0; i < myList.size();i++){
            listSliding.add(new ItemSlideMenu(R.drawable.ic_action_tiles_large, myList.get(i).toString()));
        }

        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //Icon zum öffnen oder schließen der NavigationBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setzt den Titel
        setTitle(listSliding.get(0).getTitle());
        //Ausgewähltes Item
        listViewSliding.setItemChecked(0, true);
        //Menü wird geschlossen
        drawerLayout.closeDrawer(listViewSliding);

        //Fragment Category1 wird beim Start gezeigt
        editor.putInt("catId", 0);
        replaceFragment(0);

        //Zeigt das ausgewählte Fragment an
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Setzt den Titel
                setTitle(listSliding.get(position).getTitle());
                //Ausgewähltes Item
                listViewSliding.setItemChecked(position, true);
                //Fragment wird gewechselt
                replaceFragment(position);
                //Menü wird geschlossen
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
        //Hier füllen (inflate) wir das Options Menu mit dem Menüeintrag,
        // den wir in der XML-Datei menu_menu_main.xml definiert haben.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        //Wenn "Einstellungen" gedrückt wurde, rufen wir die PrefsActivity auf
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        // Wenn "Startseite" gedrückt wurde, startet die MainActivity
        else if(item.getItemId() == R.id.home_button) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        // NavigationBar wird geöffnet
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

    //Wechselt das Fragment
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