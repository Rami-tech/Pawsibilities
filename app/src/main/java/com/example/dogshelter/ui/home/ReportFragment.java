package com.example.dogshelter.ui.home;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogshelter.R;
import com.firebase.client.Firebase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static java.lang.String.valueOf;

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;
    private Button reportBtn;
    private Button loadBtn;
    private Spinner healthCondSpinner;
    private ImageView dogImg;
    private TextInputEditText phoneEdit;
    private TextInputEditText locationEdit;
    private AlertDialog show;
    private Button cameraBtn;
    private static final int pic_id = 123;
    private SharedPreferences sp;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://pawsibilities-default-rtdb.firebaseio.com/");


    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.report_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reportBtn = getView().findViewById(R.id.reportBtn);
        healthCondSpinner = (Spinner) getView().findViewById(R.id.healthCond);
        loadBtn = getView().findViewById(R.id.loadBtn);
        dogImg = getView().findViewById(R.id.dogImg);
        phoneEdit = getView().findViewById(R.id.phoneEdit);
        locationEdit = getView().findViewById(R.id.locationEdit);
//        cameraBtn = getView().findViewById(R.id.cameraBtn);

        String[] arraySpinner = new String[]{
                "Critical", "Bad", "Good", "Excellent"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        healthCondSpinner.setAdapter(adapter);
        healthCondSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        loadBtn.findViewById(R.id.loadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);

                } else {
                    startGallery();
                }
            }
        });


//        cameraBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v)
//            {
//
//                // Create the camera_intent ACTION_IMAGE_CAPTURE
//                // it will open the camera for capture the image
//                Intent camera_intent
//                        = new Intent(MediaStore
//                        .ACTION_IMAGE_CAPTURE);
//
//                // Start the activity with camera_intent,
//                // and request pic id
//                startActivityForResult(camera_intent, pic_id);
//            }
//        });
        reportBtn.setOnClickListener(e -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Info Verification");

            builder.setMessage("Phone Number: " + phoneEdit.getText() + "\n\n" +
                    "Health Condition: " + healthCondSpinner.getSelectedItem().toString() + "\n\n" +
                    "Location: " + locationEdit.getText() +"\n\n"+
                    "Thank you for helping!");

            builder.setPositiveButton("Report", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sp = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("info", locationEdit.getText().toString());
                    editor.commit();
                    Toast.makeText(getActivity(),"Stray dog in "+sp.getString("info","")+" reported.",Toast.LENGTH_LONG).show();

                    DatabaseReference myRef = database.getReference("Dogs/Reported");

                    myRef.push();


                    DatabaseReference dog = database.getReference("Dogs/Reported/Dog"+ valueOf(System.currentTimeMillis()/1000));
                    DatabaseReference health_condition = dog.child("Health Condition");
                    health_condition.setValue(healthCondSpinner.getSelectedItem().toString());
                    DatabaseReference phoneNumber = dog.child("Phone Number");
                    phoneNumber.setValue(phoneEdit.getText().toString());
                    DatabaseReference location = dog.child("Location");
                    location.setValue(locationEdit.getText().toString());

                }
            });
            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismissDialog();
                }
            });

            show = builder.show();








        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        // TODO: Use the ViewModel
    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dogImg.setImageBitmap(bitmapImage);
            }
//            if (requestCode == pic_id) {
//
//                // BitMap is data structure of image file
//                // which stor the image in memory
//                Bitmap photo = (Bitmap)data.getExtras()
//                        .get("data");
//
//                // Set the image in imageview for display
//                dogImg.setImageBitmap(photo);
//            }


        }
    }
    private void dismissDialog() {
        show.cancel();
    }
}