package de.muensterinside.mobile;


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
        usernamePreference = (Preference)findPreference("username");

        myApp = (MuensterInsideAndroidApplication)getApplication();
        try {
            username = myApp.getUsername();
        }
        catch(Exception e){e.printStackTrace();}


        //setzt den Untertitel des Users PreferenceScreen auf username
            usernamePreference.setSummary(username);


    }



}
