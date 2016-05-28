package de.muensterinside.mobile;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;


/**
 * Created By Julia Bracht and Nicolas Burchert
 */
public class PrefsActivity extends PreferenceActivity {
    public static final String TAG = "PrefsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.prefs);

    }



}
