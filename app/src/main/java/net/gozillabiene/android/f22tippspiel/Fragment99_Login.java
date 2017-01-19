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

        EditTextPreference etp =(EditTextPreference)findPreference(getText(R.string.login_benutzername_key));
        etp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {


                Activity activity = getActivity();
                if(activity instanceof MainActivity) {
                    MainActivity myactivity = (MainActivity) activity;
                    myactivity.fragmanager = getFragmentManager();
                    myactivity.fragtrans = myactivity.fragmanager.beginTransaction();
                    myactivity.fragtrans.replace(R.id.HauptLayout, myactivity.fragment100);
                    myactivity.fragtrans.commit();
                    //myactivity.onLogin();
                    myactivity.istAdmin();
                }
                return true;

            }
        });
        EditTextPreference et1 =(EditTextPreference)findPreference(getText(R.string.login_passwort_key));
        et1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {


                Activity activity = getActivity();
                if(activity instanceof MainActivity) {
                    MainActivity myactivity = (MainActivity) activity;
                    myactivity.fragmanager = getFragmentManager();
                    myactivity.fragtrans = myactivity.fragmanager.beginTransaction();
                    myactivity.fragtrans.replace(R.id.HauptLayout, myactivity.fragment100);
                    myactivity.fragtrans.commit();
                    //myactivity.onLogin();
                    myactivity.istAdmin();
                }
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
            myactivity.istAdmin();

        }



    }
}