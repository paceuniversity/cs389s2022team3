package com.pace.lumbar.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pace.lumbar.R;
import com.pace.lumbar.chat.ChatActivity;
import com.pace.lumbar.match.Matching;
import com.pace.lumbar.setting.SettingPage;

/* Reference: https://www.youtube.com/watch?v=GuMwCuvGWx4 */

public class ProfileActivity extends AppCompatActivity {
    private ImageButton menuBtn;
    private TextView nameTxt, topicTxt, emailTxt, phoneTxt, stateTxt, detailTxt;
    private ImageView avatar;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView ivPhoto;
    private String imagePath = "";
    Activity context;
    private FirebaseUser user;
    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.match:
                        startActivity(new Intent(getApplicationContext(),
                                ChatActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                Matching.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        return true;
                }

                return false;
            }
        });

        menuBtn = findViewById(R.id.setting);
        menuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, SettingPage.class);
                startActivity(intent);
            }
        });
    }

    public void onStart(){
        super.onStart();
        data();
    }

    public void data(){
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Client");
        if(user != null){
            userID = user.getUid();
            Log.d("userid", userID);
        }

        nameTxt = findViewById(R.id.profname);
        topicTxt = findViewById(R.id.caseTopic);
        emailTxt = findViewById(R.id.emailProf);
        phoneTxt = findViewById(R.id.phoneProf);
        stateTxt = findViewById(R.id.addrProf);
        detailTxt = findViewById(R.id.topic);
        avatar = findViewById(R.id.profileImgView);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("enter", "enter");

                if(snapshot.exists()){
                    Log.d("userExist", "exist");
                    String name = snapshot.child("name").getValue().toString();
                    Log.d("userid", name);
                    String email = snapshot.child("email").getValue().toString();
                    Log.d("userid", email);
                    String phone = snapshot.child("phone").getValue().toString();
                    String address = snapshot.child("address").getValue().toString();

                    if(snapshot.child("profileIMGUri").getValue()!=null && snapshot.child("profileIMGUri").getValue().toString().length() > 0){
                        String imgUri = snapshot.child("profileIMGUri").getValue().toString();
                        avatar.setImageURI(Uri.parse(imgUri));
                    }
                    else{
                        avatar.setImageResource(R.mipmap.ic_launcher);
                    }

                    String topic = "", detail = "";

                    if(snapshot.child("firmName").exists()) {
                        detail = snapshot.child("website").getValue().toString();
                        topic = snapshot.child("firmName").getValue().toString();
                    }
                    else {
                        detail = snapshot.child("detail").getValue().toString();
                        topic = snapshot.child("topic").getValue().toString();
                    }

                    nameTxt.setText(name);
                    emailTxt.setText(email);
                    phoneTxt.setText(phone);
                    stateTxt.setText(address);
                    detailTxt.setText(detail);
                    topicTxt.setText(topic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}