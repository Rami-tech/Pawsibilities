package com.example.dogshelter.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogshelter.AdoptActivity;
import com.example.dogshelter.DonateActivity;
import com.example.dogshelter.MainActivity;
import com.example.dogshelter.R;
import com.example.dogshelter.ReportActivity;
import com.example.dogshelter.VolunteerActivity;
import com.example.dogshelter.storyActivity;

import java.util.Arrays;

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

//        api 24 android 7+ to support lambdas and streams
//        Arrays.asList(volunteerID,donateID,adoptID,reportID).forEach(e -> e.setOnClickListener(v-> {
//            mediaPlayer.start();
//            openPage(e);
//            }
//            )
//        );
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.click);
        for (CardView e : Arrays.asList(volunteerID, donateID, adoptID, reportID)) {
            e.setOnClickListener(v -> {
                        mediaPlayer.start();
                        openPage(e);
                    }
            );
        }
    }

    private void openPage(CardView e) {
        Class className = null;

        if(e==volunteerID)
            className = VolunteerActivity.class;
        else{
            if(e==donateID)
                className = DonateActivity.class;
            else{
                if(e==adoptID)
                    className = AdoptActivity.class;
                else
                    className = ReportActivity.class;
            }
        }
        if(className!=null) {
            Intent intent = new Intent(getActivity(), className);
            startActivity(intent);
        }
    }


}