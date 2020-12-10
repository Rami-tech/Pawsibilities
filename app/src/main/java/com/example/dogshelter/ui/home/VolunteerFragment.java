package com.example.dogshelter.ui.home;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dogshelter.R;
import com.google.android.material.textfield.TextInputEditText;

public class VolunteerFragment extends Fragment {

    private VolunteerViewModel volunteerViewModel;
    private Button submitBtn;
//    private input phoneNumber;
    private TextInputEditText phoneNumber, name;
    private Spinner spinner;
    private AlertDialog show;


    public static VolunteerFragment newInstance() {
        return new VolunteerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.volunteer_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        volunteerViewModel = new ViewModelProvider(this).get(VolunteerViewModel.class);
        // TODO: Use the ViewModel


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitBtn = getView().findViewById(R.id.volunteerBtn);
        spinner = (Spinner) getView().findViewById(R.id.centerSpinner);
        phoneNumber = getView().findViewById(R.id.phoneNumber);
        name = getView().findViewById(R.id.name);

        String[] arraySpinner = new String[] {
                "Location 1", "Location 2", "Location 3", "Location 4", "Location 5", "Location 6", "Location 7"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity() ,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        submitBtn.setOnClickListener(e ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Information check");

//            final TextView textView = new TextView(getActivity());
//            textView.setText("By submitting a team member will contact you!");
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//            textView.setLayoutParams(lp);
//            builder.setView(textView);
            builder.setMessage("Name: "+name.getText()+"\n\n"+
                                "Phone: "+phoneNumber.getText()+"\n\n"+
                                "Center Location: "+spinner.getSelectedItem().toString()+"\n\n"+
                    "By submitting this form, a team member will contact you!");

            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
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

    private void dismissDialog() {
        show.cancel();
    }
}