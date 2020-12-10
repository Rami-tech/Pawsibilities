package com.example.dogshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dogshelter.ui.home.DonateFragment;

public class DonateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DonateFragment.newInstance())
                    .commitNow();
        }
    }
}