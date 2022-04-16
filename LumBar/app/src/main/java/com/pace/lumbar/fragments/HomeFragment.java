package com.pace.lumbar.fragments;

import static android.content.ContentValues.TAG;

import android.gesture.Gesture;
import android.nfc.Tag;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pace.lumbar.R;


public class HomeFragment extends Fragment {
    //widgets
    private ImageView imageView;

    //vars
    private GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

}