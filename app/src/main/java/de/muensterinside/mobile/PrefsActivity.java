package de.muensterinside.mobile;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;



/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public class PrefsActivity extends PreferenceActivity {
    public static final String TAG = "PrefsActivity";
    Preference usernamePreference;
    private MuensterInsideAndroidApplication myApp;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

        //Preference zum Anzeigen des Username wird erstellt
        usernamePreference = (Preference)findPreference("username");

        myApp = (MuensterInsideAndroidApplication)getApplication();
        try {
            //Abrufen des Usernames
            username = myApp.getUsername();
        }
        catch(Exception e){e.printStackTrace();}


        //setzt den Untertitel des Users Preference auf username
            usernamePreference.setSummary(username);


        }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    }



