package de.muensterinside.mobile.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.tasks.LocationListTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class Category2 extends Fragment{

    public Category2(){

    }

    public static final String TAG = "Category2";
    private int cat_id;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);

        // Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getActivity().getIntent();

        /**
         * Hier wird explizit auf die übergebende Id,
         * der ausgewählten Category zugegriffen,
         * damit wir auch nur die Locations laden,
         * die zu der ausgewählten Category gehören.
         */
        SharedPreferences boolPref = getActivity().getSharedPreferences("MyBoolPref", Context.MODE_PRIVATE);
        SharedPreferences myCatIdPref = getActivity().getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        if(boolPref.getBoolean("newLocationBool", false)== true){
            cat_id = boolPref.getInt("cat_id", 0);
        }
        else {
            cat_id = myCatIdPref.getInt("catId", 0);
        }

        /* In einer SharedPreference wird die vorher ausgewählte
         * ID einer Kategorie gespeichert.
         */
        SharedPreferences.Editor editor = myCatIdPref.edit();
        editor.putInt("catId", cat_id);
        editor.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_category, container, false);
        // Es wird ein Application Objekt erzeugt
        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getActivity().getApplication();

        // Es wird eine ListView erzeugt um eine Liste von Locations anzuzeigen
        ListView listView = (ListView) rootView.findViewById(R.id.categoryList);

        // Es wird ein Button erzeugt um eine neue Location anlegen zu können
        Button newLocation = (Button) rootView.findViewById(R.id.newLocation);

        // Der LocationListTask wird aufgerufen
        LocationListTask locationListTask = new LocationListTask(getActivity(), cat_id, myApp, listView, newLocation);
        locationListTask.execute();

        return rootView;
    }
}
