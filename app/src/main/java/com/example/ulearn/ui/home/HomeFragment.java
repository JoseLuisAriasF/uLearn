package com.example.ulearn.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ulearn.R;
import com.example.ulearn.activity_mate;
import com.example.ulearn.activity_extras;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final LinearLayout linearMatematica = root.findViewById(R.id.linearMatematica);
        linearMatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_mate.class);
                getActivity().startActivity(intent);
            }
        });

        final LinearLayout linearMemoriayReaccion = root.findViewById(R.id.linearMemoriayReaccion);
        linearMemoriayReaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_extras.class);
                getActivity().startActivity(intent);
            }
        });


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
          //      textView.setText(s);

            }
        });
        return root;
    }
}