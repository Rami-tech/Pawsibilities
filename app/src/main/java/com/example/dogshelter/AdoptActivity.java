package com.example.dogshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dogshelter.ui.home.AdoptFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdoptActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://pawsibilities-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adopt_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AdoptFragment.newInstance())
                    .commitNow();
        }

        DatabaseReference myRef = database.getReference("Dogs");
        myRef.addValueEventListener(new ValueEventListener() {

            private final String TAG = MainActivity.class.getSimpleName();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                int i=1;
                while(i<=map.size()){
                    System.out.println(map.get("Dog" + String.valueOf(i)));
                    i++;
                }

                DatabaseReference breed = database.getReference("Dogs/Dog4/Breed");
                breed.setValue("pug");
                DatabaseReference age = database.getReference("Dogs/Dog4/Age");
                age.setValue("9");
                DatabaseReference name = database.getReference("Dogs/Dog4/name");
                name.setValue("geo");



            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });





    }
}