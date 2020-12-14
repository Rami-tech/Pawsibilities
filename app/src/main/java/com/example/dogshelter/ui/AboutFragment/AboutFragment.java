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

public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;
    private Button webViewBtn;

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
        webViewBtn.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), placeHolderActivity.class);
            startActivity(intent);
        });
    }

}