package com.pace.lumbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/* Reference: https://www.youtube.com/watch?v=GuMwCuvGWx4 */

public class ProfilePage extends AppCompatActivity {
    private ImageButton menuBtn;
    private TextView nameTxt, topicTxt, emailTxt, phoneTxt, stateTxt, detailTxt;
    private ImageView profIMG, emailIMG, phoneIMG, addIMG, detailIMG;
    private String email, password;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USER = "users";

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        menuBtn= findViewById(R.id.setting);
        menuBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfilePage.this, SettingPage.class);
                startActivity(intent);
            }
        });

        nameTxt = findViewById(R.id.profname);
        topicTxt = findViewById(R.id.caseTopic);
        emailTxt = findViewById(R.id.emailProf);
        phoneTxt = findViewById(R.id.phoneProf);
        stateTxt = findViewById(R.id.addrProf);
        detailTxt = findViewById(R.id.topic);

        profIMG = findViewById(R.id.profileImgView);
        emailIMG = findViewById(R.id.emailPic);
        phoneIMG = findViewById(R.id.phonePic);
        addIMG = findViewById(R.id.locatePic);
        detailIMG = findViewById(R.id.topicPic);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USER);

        userRef.addValueEventListener(new ValueEventListener() {
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
    }
}
