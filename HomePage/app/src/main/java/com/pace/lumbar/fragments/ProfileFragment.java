package com.pace.lumbar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.pace.lumbar.R;
import com.pace.lumbar.SplashActivity;
import com.pace.lumbar.aboutpage.AboutPage;



public class ProfileFragment extends Fragment {

    private Button button1;
    private Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        button1 = view.findViewById(R.id.aboutPage);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AboutPage.class);
                startActivity(intent);
            }
        });

        button2 = view.findViewById(R.id.signOut);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}