package com.pace.lumbar.fragments;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import com.pace.lumbar.match.Matching;
import com.pace.lumbar.R;


public class HomeFragment extends Fragment {

    private Button matchingBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        matchingBtn = view.findViewById(R.id.goTo_matching);
        matchingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Matching.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;

    }
}