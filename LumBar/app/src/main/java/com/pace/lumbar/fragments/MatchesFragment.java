package com.pace.lumbar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.pace.lumbar.R;


public class MatchesFragment extends Fragment {

    private TextView matchID, matchName;
    private ImageView matchPic;
//    private MatchesFragment(View view){
//        view.setOnClickListener((View.OnClickListener) this);
//        matchID = (TextView) view.findViewById(R.id.matchID);
//        matchName = (TextView) view.findViewById(R.id.matchName);
//        matchPic = (ImageView) view.findViewById(R.id.matchPic);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }
}