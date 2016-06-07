package de.muensterinside.mobile.tasks;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.CategoryActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.adapter.SlidingMenuAdapter;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.fragments.Category1;
import de.muensterinside.mobile.fragments.Category2;
import de.muensterinside.mobile.fragments.Category3;
import de.muensterinside.mobile.fragments.Category4;
import de.muensterinside.mobile.fragments.Category5;
import de.muensterinside.mobile.fragments.Category6;
import de.muensterinside.mobile.model.ItemSlideMenu;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class CategoryTask extends AsyncTask<Void, Void, List<Category>> {
    private MuensterInsideAndroidApplication myApp;
    private List<Category> categories;
    private Context context;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private SharedPreferences.Editor editor;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static final String TAG = "CategoryTask";

    /* Der Konstruktor erwartet ein Context Objekt,
     * ein Application Objekt und ein ListView Objekt.
     */
    public CategoryTask(Context context, MuensterInsideAndroidApplication myApp,
                        ListView listViewSliding, DrawerLayout drawerLayout, List<ItemSlideMenu> listSliding,
                        SlidingMenuAdapter adapter, SharedPreferences.Editor editor,
                        ActionBarDrawerToggle actionBarDrawerToggle) {
        this.context = context;
        this.myApp = myApp;
        this.listViewSliding = listViewSliding;
        this.listSliding = listSliding;
        this.drawerLayout = drawerLayout;
        this.adapter = adapter;
        this.editor = editor;
        this.actionBarDrawerToggle = actionBarDrawerToggle;
    }

    // Im Hintergrund soll der Webservice aufgerufen werden
    @Override
    protected List<Category> doInBackground(Void... params) {
        Log.d(TAG, "doInBackground() gestartet");
        try {
            // Die Methode getCategories liefert eine Liste zurück
            this.categories = myApp.getMuensterInsideImpl().getCategories();
            Log.i(TAG, "doInBackground() erfolgreich");
            return this.categories;
        } catch (Exception e) {
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    /* Die onPostExecute Methode erwartet eine Liste,
     * in der Category Objekte gespeichert sind.
     */
    @Override
    protected void onPostExecute(List<Category> categories) {
        Log.d(TAG, "onPostExecute() gestartet");

        // Die Namen der Kategorien werden in eine Liste gespeichert
        List myList = new ArrayList<String>();
        for (int i = 0; i < categories.size(); i++) {
            myList.add(categories.get(i).getName());
        }

        // NavigationBar
        listSliding = new ArrayList<>();
        //Fügt Items in die NavigationBar ein
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, myList.get(0).toString()));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, myList.get(1).toString()));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, myList.get(2).toString()));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, myList.get(3).toString()));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, myList.get(4).toString()));
        listSliding.add(new ItemSlideMenu(R.drawable.ic_action_settings, myList.get(5).toString()));
        adapter = new SlidingMenuAdapter(context, listSliding);
        listViewSliding.setAdapter(adapter);

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

        if (null != fragment) {
            FragmentManager fragmentManager = ((ActionBarActivity) context).getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
