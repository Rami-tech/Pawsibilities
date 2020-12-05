package com.example.dogshelter.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dogshelter.MainActivity;
import com.example.dogshelter.R;
import com.example.dogshelter.storyActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private CardView story1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        return root;

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        story1 = getView().findViewById(R.id.story1);
        story1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), storyActivity.class);
            startActivity(intent);
        });

    }
}