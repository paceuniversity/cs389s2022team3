package com.pace.lumbar.fragments;

import android.app.Activity;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pace.lumbar.Case;
import com.pace.lumbar.Client;
import com.pace.lumbar.R;
import com.pace.lumbar.SettingPage;


public class ProfileFragment extends Fragment {
    private ImageButton menuBtn;
    private TextView nameTxt, topicTxt, emailTxt, phoneTxt, stateTxt, detailTxt;
    private ImageView avatar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID;
    private Activity context;

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

        context = getActivity();
        return view;
    }

    public void onStart(){
        super.onStart();
        data();
    }

    public void data(){
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference("Users");
        userID = firebaseUser.getUid();

        nameTxt = context.findViewById(R.id.profname);
        topicTxt = context.findViewById(R.id.caseTopic);
        emailTxt = context.findViewById(R.id.emailProf);
        phoneTxt = context.findViewById(R.id.phoneProf);
        stateTxt = context.findViewById(R.id.addrProf);
        detailTxt = context.findViewById(R.id.topic);
        avatar = context.findViewById(R.id.profileImgView);

        userRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Client userProf = snapshot.getValue(Client.class);
                Case userCase = snapshot.getValue(Case.class);

                if(userProf!=null){
                    nameTxt.setText((userProf.getRealName()));
                    topicTxt.setText((userCase.getCaseType()));
                    emailTxt.setText((userProf.getEmail()));
                    phoneTxt.setText((userProf.getPhoneNumber()));
                    stateTxt.setText((userProf.getState()));
                    detailTxt.setText((userCase.getCaseDetails()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}