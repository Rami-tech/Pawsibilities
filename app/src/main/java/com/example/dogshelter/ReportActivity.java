package com.example.dogshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dogshelter.ui.home.ReportFragment;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ReportFragment.newInstance())
                    .commitNow();
        }
    }
}