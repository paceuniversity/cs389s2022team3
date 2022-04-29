package com.pace.lumbar.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.pace.lumbar.account.Client;
import com.pace.lumbar.R;
import com.pace.lumbar.SettingPage;
import com.pace.lumbar.account.Lawyer;


public class ProfileFragment extends Fragment {
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
    private String userID, userType ="";

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
//        setupFirebaseAuth();

        return view;
    }

    public void onStart(){
        super.onStart();
        data();
    }

    public void data(){
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        Log.d("userid", userType);
        if(user != null) {
            userID = user.getUid();
            Log.d("userid", userID);
        }

        nameTxt = context.findViewById(R.id.profname);
        topicTxt = context.findViewById(R.id.caseTopic);
        emailTxt = context.findViewById(R.id.emailProf);
        phoneTxt = context.findViewById(R.id.phoneProf);
        stateTxt = context.findViewById(R.id.addrProf);
        detailTxt = context.findViewById(R.id.topic);
        avatar = context.findViewById(R.id.profileImgView);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Client userProfile = snapshot.getValue(Client.class);
                    Lawyer userProf = snapshot.getValue(Lawyer.class);

                    if(userProfile!=null) {
                        String name = userProfile.getName();
                        Log.d("userid1", name);
                        String email = userProfile.getEmail();
                        Log.d("userid1", email);
                        String phone = userProfile.getPhone();
                        String address = userProfile.getAddress();
                        String prof = userProfile.getProfileIMGUri();


//                    Case clientCase = userProfile.getCase();
                        String topic = userProfile.getTopic();
                        String detail = userProfile.getDetail();

                        Log.d("userid1", detail);

                        nameTxt.setText(name);
                        emailTxt.setText(email);
                        phoneTxt.setText(phone);
                        stateTxt.setText(address);
                        avatar.setImageURI(Uri.parse(prof));
                        detailTxt.setText(detail);
                        topicTxt.setText(topic);
                    }

                    Log.d("userid", "not Client");
                    if(userProf != null){
                        String name = userProf.getName();
                        String email = userProf.getEmail();
                        String phone = userProf.getPhone();
                        String address = userProf.getFirm().getAddress();
                        String prof = userProf.getProfileIMGUri();

                        String topic = userProf.getFirm().getFirmName();
                        String detail = userProf.getFirm().getWebsite();

                        nameTxt.setText(name);
                        emailTxt.setText(email);
                        phoneTxt.setText(phone);
                        stateTxt.setText(address);
                        avatar.setImageURI(Uri.parse(prof));
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