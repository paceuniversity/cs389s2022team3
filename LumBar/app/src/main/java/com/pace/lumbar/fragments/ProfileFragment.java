package com.pace.lumbar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pace.lumbar.R;
import com.pace.lumbar.SettingPage;


public class ProfileFragment extends Fragment {

    private ImageButton menuBtn;
    private TextView nameTxt, topicTxt, emailTxt, phoneTxt, stateTxt, detailTxt;
    private ImageView avatar;
    private String email, password;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        menuBtn= view.findViewById(R.id.setting);
        menuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), SettingPage.class);
                startActivity(intent);
            }
        });

        nameTxt = view.findViewById(R.id.profname);
        topicTxt = view.findViewById(R.id.caseTopic);
        emailTxt = view.findViewById(R.id.emailProf);
        phoneTxt = view.findViewById(R.id.phoneProf);
        stateTxt = view.findViewById(R.id.addrProf);
        detailTxt = view.findViewById(R.id.topic);
        avatar = view.findViewById(R.id.profileImgView);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");
        Query query = userRef.orderByChild("realName").equalTo(firebaseUser.getDisplayName());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        nameTxt.setText((ds.child("realName").getValue(String.class)));
                        topicTxt.setText((ds.child("caseType").getValue(String.class)));
                        emailTxt.setText((ds.child("email").getValue(String.class)));
                        phoneTxt.setText((ds.child("phoneNumber").getValue(String.class)));
                        stateTxt.setText((ds.child("state").getValue(String.class)));
                        detailTxt.setText((ds.child("caseDetails").getValue(String.class)));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}