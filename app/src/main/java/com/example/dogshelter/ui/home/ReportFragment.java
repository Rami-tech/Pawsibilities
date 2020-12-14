package com.example.dogshelter.ui.home;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.dogshelter.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ReportFragment extends Fragment {

    private ReportViewModel mViewModel;
    private Button reportBtn;
    private Button loadBtn;
    private Spinner healthCondSpinner;
    private ImageView dogImg;
    private TextInputEditText phoneEdit;
    private TextInputEditText locationEdit;
    private AlertDialog show;


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


        }
    }
    private void dismissDialog() {
        show.cancel();
    }
}