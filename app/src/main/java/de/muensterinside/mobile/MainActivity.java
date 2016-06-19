package de.muensterinside.mobile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.adapter.SlidingMenuAdapter;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.fragments.Category1;
import de.muensterinside.mobile.fragments.Category2;
import de.muensterinside.mobile.fragments.Category3;
import de.muensterinside.mobile.fragments.Category4;
import de.muensterinside.mobile.fragments.Category5;
import de.muensterinside.mobile.fragments.Category6;
import de.muensterinside.mobile.fragments.Home;
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
         * ID einer Kategorie  ausgelesen.
         */
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        //repräsentiert den übergreifenden Zustand einer App
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getApplication();

        //Netz erreichbar vohranden ? Konnektivität wird geprüft.
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Kategorien werden im Hintergrund geladen
            CategoryTask categoryTask = new CategoryTask(this, myApp);
            categoryTask.execute();

            List<Category> categories;
            try {
                // Kategorien aus dem CategoryTask werden für die Weiterverwendung gespeichert
                categories = categoryTask.get();
            } catch (Exception e) {
                //Wenn keine Kategorien vorhanden sind, werden die Kategorien auf null gesetzt
                categories = null;
                e.printStackTrace();
            }

            // Die Namen der Kategorien werden in eine Liste gespeichert
            List myList = new ArrayList<String>();
            for (int i = 0; i < categories.size(); i++) {
                myList.add(categories.get(i).getName());
            }

            // NavigationBar
            //Die ListView der einzelnen Fragments
            listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            listSliding = new ArrayList<>();
            listSliding.add(new ItemSlideMenu(R.drawable.ic_action_tiles_large, "Startseite"));
            //Fügt Items in die NavigationBar ein
            for (int i = 0; i < myList.size(); i++) {
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
            SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            boolean test = pref.getBoolean("test", false);

            if (test) {
                replaceFragment(1);
                Log.i(TAG, "Fragment 1 wird angezeigt");
            } else {
                replaceFragment(0);
                Log.i(TAG, "Fragment 0 (Startseite) wird angezeigt");
            }

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
                    Log.i(TAG, "Fragment: " + position + " wird angezeigt");
                    //Menü wird geschlossen
                    drawerLayout.closeDrawer(listViewSliding);
                }
            });

            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {

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
        } else {

            //Ist keine Verbindung vorhanden, passiert nichts und der Toast wird ausgegeben (Verbindung fehlgeschlagen)
            Log.d(TAG, "Keine Internetverbindung");
            Toast.makeText(MainActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
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
                fragment = new Home();
                break;
            case 1:
                fragment = new Category1();
                editor.putInt("catId", 0);
                editor.commit();
                break;
            case 2:
                fragment = new Category2();
                editor.putInt("catId", 1);
                editor.commit();
                break;
            case 3:
                fragment = new Category3();
                editor.putInt("catId", 2);
                editor.commit();
                break;
            case 4:
                fragment = new Category4();
                editor.putInt("catId", 3);
                editor.commit();
                break;
            case 5:
                fragment = new Category5();
                editor.putInt("catId", 4);
                editor.commit();
                break;
            default:
                fragment = new Home();
                break;
        }

        //führt dieses aus, wenn keine Kategorien vorhanden sind
        if(null!=fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
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
        // Wenn "Home" gedrückt wurde, rufen wir die MainActivity auf
        else if(item.getItemId() == R.id.action_home){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        // Wenn die Navigationbar gedrückt wurde, rufen wir diese auf
        else if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

}