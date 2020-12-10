package com.example.dogshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dogshelter.ui.home.VolunteerFragment;

public class VolunteerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, VolunteerFragment.newInstance())
                    .commitNow();
        }
    }
}