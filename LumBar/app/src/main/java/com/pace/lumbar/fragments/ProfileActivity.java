package com.pace.lumbar.fragments;

import android.app.Activity;
import android.content.Intent;
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
import com.pace.lumbar.account.Client;
import com.pace.lumbar.R;
import com.pace.lumbar.SettingPage;


public class ProfileActivity extends AppCompatActivity {
    private ImageButton menuBtn;
    private TextView nameTxt, topicTxt, emailTxt, phoneTxt, stateTxt, detailTxt;
    private ImageView avatar;
    //    private FirebaseUser firebaseUser;
//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference userRef;
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private String userID;
//    private Activity context;
//    private FirebaseAuth firebaseAuth;
//    private FirebaseAuth.AuthStateListener authStateListener;
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
                                MatchesActivity.class));
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
                Client userProfile = snapshot.getValue(Client.class);

                if(userProfile!=null){
                    String name = userProfile.getName();
                    Log.d("userid", name);
                    String email = userProfile.getEmail();
                    Log.d("userid", email);
                    String phone = userProfile.getPhoneNumber();
                    String state = userProfile.getState();
                    String topic = userProfile.getCaseType();
                    String detail = userProfile.getCaseDetails();

                    nameTxt.setText(name);
                    emailTxt.setText(email);
                    phoneTxt.setText(phone);
                    stateTxt.setText(state);
                    detailTxt.setText(detail);
                    topicTxt.setText(topic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

//    private void setupFirebaseAuth(){
//        firebaseAuth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        userRef = database.getReference();
//
//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user!=null){
//                    userID = firebaseUser.getUid();
//                    Log.d("userid", userID);
//                }
//                else{
//                    Log.d("userid", "failed to get Authuser");
//                }
//            }
//        };
//
//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //retrieve info
////                String value = snapshot.getValue(String.class);
////                Log.d("userid", "Value is: " + value);
//                showData(snapshot);
//
//                //retrieve images
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    private void showData(DataSnapshot snapshot) {
//        for(DataSnapshot ds : snapshot.getChildren()){
//            Client client = new Client();
//            Case clientCase = new Case();
//            client.setRealName(ds.child(userID).getValue(Client.class).getRealName());
//            client.setEmail(ds.child(userID).getValue(Client.class).getEmail());
//            client.setPhoneNumber(ds.child(userID).getValue(Client.class).getPhoneNumber());
//            client.setState(ds.child(userID).getValue(Client.class).getState());
//            clientCase.setCaseType(ds.child(userID).getValue(Case.class).getCaseType());
//            clientCase.setCaseDetails(ds.child(userID).getValue(Case.class).getCaseDetails());
//
//            Log.d("userid", client.getRealName());
//            Log.d("userid", client.getEmail());
//            Log.d("userid", client.getPhoneNumber());
//            Log.d("userid", clientCase.getCaseType());
//            Log.d("userid", clientCase.getCaseDetails());
//            Log.d("userid", client.getState());
//
//            ArrayList<String> array = new ArrayList<>();
//            array.add(client.getRealName());
//            array.add(client.getEmail());
//            array.add(client.getPhoneNumber());
//            array.add(client.getState());
//            array.add(clientCase.getCaseType());
//            array.add(clientCase.getCaseDetails());
//
//            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.fragment_profile, array);
//
//        }
//    }
//
//    public void onStart(){
//        super.onStart();
//        firebaseAuth.addAuthStateListener(authStateListener);
//    }
//    public void onStop(){
//        super.onStop();
//        if(authStateListener!=null){
//            firebaseAuth.removeAuthStateListener(authStateListener);
//        }
//    }


//    public void onStart(){
//        super.onStart();
//        data();
//    }
//
//    public void data(){
//        super.onStart();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        userRef = FirebaseDatabase.getInstance().getReference("Client");
//        if(firebaseUser != null){
//            userID = firebaseUser.getUid();
//            Log.d("userid", userID);
//        }
//
//        nameTxt = context.findViewById(R.id.profname);
//        topicTxt = context.findViewById(R.id.caseTopic);
//        emailTxt = context.findViewById(R.id.emailProf);
//        phoneTxt = context.findViewById(R.id.phoneProf);
//        stateTxt = context.findViewById(R.id.addrProf);
//        detailTxt = context.findViewById(R.id.topic);
//        avatar = context.findViewById(R.id.profileImgView);
//
//        userRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("userid", "in snapshot");
//                Client userProf = snapshot.getValue(Client.class);
//                Case userCase = snapshot.getValue(Case.class);
//
//                if(userProf!=null){
//                    nameTxt.setText((userProf.getRealName()));
//                    Log.d("userid", nameTxt.getText().toString());
//                    topicTxt.setText((userCase.getCaseType()));
//                    Log.d("userid", topicTxt.getText().toString());
//                    emailTxt.setText((userProf.getEmail()));
//                    Log.d("userid", nameTxt.getText().toString());
//                    phoneTxt.setText((userProf.getPhoneNumber()));
//                    Log.d("userid", nameTxt.getText().toString());
//                    stateTxt.setText((userProf.getState()));
//                    Log.d("userid", nameTxt.getText().toString());
//                    detailTxt.setText((userCase.getCaseDetails()));
//                    Log.d("userid", userCase.getCaseDetails());
//                }
//                else{
//                    Log.d("userid", "user null");
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        userRef.child("users").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.d("userid", "Error getting data", task.getException());
//                }
//                else {
//                    Log.d("userid", String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });
//    }
}