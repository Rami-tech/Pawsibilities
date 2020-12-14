package com.example.dogshelter.ui.AboutFragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dogshelter.R;
import com.example.dogshelter.placeHolderActivity;
import com.example.dogshelter.storyActivity;
import com.example.dogshelter.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;
    private Button webViewBtn;
    private Button HDLocationID;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        webViewBtn = getView().findViewById(R.id.webViewBtn);
        HDLocationID = getView().findViewById(R.id.HDLocationId);
        webViewBtn.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), placeHolderActivity.class);
            startActivity(intent);
        });
        HDLocationID.setOnClickListener(v->{
//            BottomNavigationView navigationView = (BottomNavigationView) getActivity().findViewById(R.id.nav_view);
//            navigationView.getMenu().getItem(2).setChecked(true);

            getActivity().findViewById(R.id.navigation_notifications).performClick();

        });
    }

}