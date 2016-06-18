package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;


import java.util.List;


import de.muensterinside.mobile.MuensterInsideAndroidApplication;


import de.muensterinside.mobile.entities.Category;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class CategoryTask extends AsyncTask<Void, Void, List<Category>> {
    private MuensterInsideAndroidApplication myApp;
    private List<Category> categories;
    private Context context;
    public static final String TAG = "CategoryTask";

    /* Der Konstruktor erwartet ein Context Objekt und
     * ein Application Objekt.
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




}
