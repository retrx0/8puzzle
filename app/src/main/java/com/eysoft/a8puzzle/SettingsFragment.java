package com.eysoft.a8puzzle;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import view.Piece;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static Boolean enableTimer = false;
    public final static String darkModeKey = "darkmode_switch_pref";
    public final static String enableTimePrefKey = "display_time_pref";
    public final static String gridSizePrefKey = "grid_size";
    public final String autoDarkModeCheckBokPrefKey = "auto_dark_pref_key";

    public static boolean isAutoDarkMode;
    public static boolean isPrefDarkModeEnabled;

    SwitchPreference darkModeSwitch;

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
        SwitchPreference enableTime = findPreference(enableTimePrefKey);
        assert enableTime != null;
        if(enableTime.isChecked()){
            enableTimer = true;
        }
        else{
            enableTimer = false;
        }
        CheckBoxPreference autoDarkMode = findPreference(autoDarkModeCheckBokPrefKey);
        assert autoDarkMode != null;
        if (autoDarkMode.isChecked()){
            isAutoDarkMode = true;
        }else {
            isAutoDarkMode = false;
        }

        darkModeSwitch = findPreference(darkModeKey);
        if (isAutoDarkMode){
            int nightModeFlags = getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:
                    assert darkModeSwitch != null;
                    darkModeSwitch.setChecked(true);
                    isPrefDarkModeEnabled = true;
                    break;
                case Configuration.UI_MODE_NIGHT_NO:
                    assert darkModeSwitch != null;
                    darkModeSwitch.setChecked(false);
                    isPrefDarkModeEnabled = false;
                    break;
                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    break;
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(darkModeKey)){
            if (darkModeSwitch.isChecked()){
                getContext().setTheme(R.style.StandardDark);
                MainActivity.setDarkMode(true);
                System.out.println("DM "+isPrefDarkModeEnabled);
            }else{
                getContext().setTheme(R.style.StandardLight);
                MainActivity.setDarkMode(false);
                System.out.println("DM "+isPrefDarkModeEnabled);
            }
        }
        else if (key.equals(SettingsFragment.enableTimePrefKey)){

        }
    }
}