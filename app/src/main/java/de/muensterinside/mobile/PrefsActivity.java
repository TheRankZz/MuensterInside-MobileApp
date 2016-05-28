package de.muensterinside.mobile;


import android.os.Bundle;
import android.preference.PreferenceActivity;


/**
 * Created By Julia Bracht and Nicolas Burchert
 */
public class PrefsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.prefs);

    }



}
