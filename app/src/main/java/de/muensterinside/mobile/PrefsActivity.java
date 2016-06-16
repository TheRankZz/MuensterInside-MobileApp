package de.muensterinside.mobile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */

public class PrefsActivity extends PreferenceActivity {
    public static final String TAG = "PrefsActivity";
    EditTextPreference usernameEditTextPreference;
    private MuensterInsideAndroidApplication myApp;
    String huhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        usernameEditTextPreference = (EditTextPreference)findPreference("username");

        try {
            huhu = myApp.getUsername();
        }
        catch(Exception e){e.printStackTrace();}


            usernameEditTextPreference.setSummary(huhu);


    }



}
