package net.gozillabiene.android.f22tippspiel;

import android.app.Activity;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class Fragment99_Login extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.login);

        EditTextPreference etp =(EditTextPreference)findPreference("benutzername");
        etp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {


            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                Activity activity = getActivity();
                //if(activity instanceof MainActivity) {
                    MainActivity myactivity = (MainActivity) activity;
                    myactivity.onLogin();
                //}
                return true;

            }
        });



    }


    public void onPause() {
        super.onPause();

        Activity activity = getActivity();
        if(activity instanceof MainActivity){
            MainActivity myactivity = (MainActivity) activity;
            myactivity.onLogin();


        }



    }
}