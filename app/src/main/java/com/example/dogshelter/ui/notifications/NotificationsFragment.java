package com.example.dogshelter.ui.notifications;

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

import com.example.dogshelter.R;
import com.example.dogshelter.storyActivity;
import com.example.dogshelter.ui.dashboard.DashboardViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NotificationsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;


    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng loc1 = new LatLng(33.948738, 35.674530);
        googleMap.addMarker(new MarkerOptions().position(loc1).title("Headquarter"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc1));

        LatLng loc2 = new LatLng(33.974223, 35.684111);
        googleMap.addMarker(new MarkerOptions().position(loc2).title("Headquarter"));

        LatLng loc3 = new LatLng(33.938670, 35.631055);
        googleMap.addMarker(new MarkerOptions().position(loc3).title("Headquarter"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc3,18));


    }
}