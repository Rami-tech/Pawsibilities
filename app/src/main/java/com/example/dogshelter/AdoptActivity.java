package com.example.dogshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dogshelter.ui.home.AdoptFragment;

public class AdoptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adopt_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AdoptFragment.newInstance())
                    .commitNow();
        }
//        getSupportActionBar().setTitle("Adopt");
    }
}