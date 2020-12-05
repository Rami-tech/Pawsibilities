package com.example.dogshelter.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogshelter.MainActivity;
import com.example.dogshelter.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CardView volunteerID, donateID, adoptID,reportID;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        volunteerID = getView().findViewById(R.id.volunteerID);
        donateID = getView().findViewById(R.id.donateID);
        adoptID = getView().findViewById(R.id.adoptCardId);
        reportID = getView().findViewById(R.id.reportId);
        volunteerID.setOnClickListener(v-> {
            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.click);
            mediaPlayer.start();
            });

    }


}