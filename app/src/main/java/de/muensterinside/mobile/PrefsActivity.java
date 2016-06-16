package de.muensterinside.mobile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public class PrefsActivity extends PreferenceActivity {
    public static final String TAG = "PrefsActivity";
    PreferenceScreen usernamePreference;
    private MuensterInsideAndroidApplication myApp;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        usernamePreference = (PreferenceScreen)findPreference("username");

        try {
            username = myApp.getUsername();
        }
        catch(Exception e){e.printStackTrace();}


            usernamePreference.setSummary(username);


    }



}
