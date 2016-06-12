package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;

import android.widget.ListView;


import java.util.List;


import de.muensterinside.mobile.MuensterInsideAndroidApplication;

import de.muensterinside.mobile.adapter.SlidingMenuAdapter;
import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.model.ItemSlideMenu;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class CategoryTask extends AsyncTask<Void, Void, List<Category>> {
    private MuensterInsideAndroidApplication myApp;
    private List<Category> categories;
    private Context context;
    public static final String TAG = "CategoryTask";

    /* Der Konstruktor erwartet ein Context Objekt,
     * ein Application Objekt und ein ListView Objekt.
     */
    public CategoryTask(Context context, MuensterInsideAndroidApplication myApp) {
        this.context = context;
        this.myApp = myApp;
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



    }


}
