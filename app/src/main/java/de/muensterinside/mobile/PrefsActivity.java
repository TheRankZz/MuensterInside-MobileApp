package de.muensterinside.mobile;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Julia on 10.05.2016.
 */
public class PrefsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.prefs);
    }
}
