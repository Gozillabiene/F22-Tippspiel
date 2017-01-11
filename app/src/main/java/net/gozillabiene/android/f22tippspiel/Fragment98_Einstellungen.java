package net.gozillabiene.android.f22tippspiel;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class Fragment98_Einstellungen extends PreferenceFragment implements Preference.OnPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.einstellungen);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
