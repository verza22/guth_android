package com.example.guth.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guth.R;
import com.example.guth.Views.AcercaViewModel;
import com.example.guth.Views.HomeViewModel;

public class AcercaFragment extends Fragment {

    private AcercaViewModel acercaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        acercaViewModel =
                ViewModelProviders.of(this).get(AcercaViewModel.class);
        View root = inflater.inflate(R.layout.acerca_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_acerca);
        acercaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}