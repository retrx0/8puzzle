package com.eysoft.a8puzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(findViewById(R.id.settings_fragment) != null){
            if (savedInstanceState != null)
                return;
            getSupportFragmentManager().beginTransaction().add(R.id.settings_fragment, new SettingsFragment()).commit();
        }

    }



}