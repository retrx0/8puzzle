package com.eysoft.a8puzzle;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static Boolean enableTimer = false;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        if(!GridSelectFragment.selection.equals("")){
            switch (GridSelectFragment.selection){
                case "3x3":
                    findPreference("grid_size").setDefaultValue(R.string.list_item_1);
                    break;
                case "4x4":
                    break;
            }
        }

        if(findPreference("display_time_pref").isEnabled()){
            enableTimer = true;
        }
        else{
            enableTimer = false;
        }
    }

}