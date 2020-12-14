package com.example.dogshelter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.dogshelter.ui.home.DonateFragment;
import com.example.dogshelter.ui.notifications.NotificationsFragment;

public class NotificationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new NotificationsFragment())
                    .commitNow();
        }
    }
}
