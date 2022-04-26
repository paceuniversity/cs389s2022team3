package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.pace.lumbar.account.LoginPage;
import com.pace.lumbar.dupAboutPage.AboutPageDup;
import com.pace.lumbar.fragments.ProfileFragment;

public class SettingPage extends AppCompatActivity {

    private Button signOut, aboutBtn, helpBtn, editBtn, privacyBtn;
    private ImageButton revertBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);

        mAuth = FirebaseAuth.getInstance();

        signOut = findViewById(R.id.signout);
        signOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAuth.signOut();
                Intent intent = new Intent(SettingPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

        aboutBtn = findViewById(R.id.about);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingPage.this, AboutPageDup.class);
                startActivity(intent);
            }
        });

        helpBtn = findViewById(R.id.help);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingPage.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        revertBtn = findViewById(R.id.revertToProf);
        revertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        privacyBtn = findViewById(R.id.privacyBtn);
        privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingPage.this, TermsPrivacy.class);
                startActivity(intent);
            }
        });

        editBtn = findViewById(R.id.edit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingPage.this, EditProfile.class);
                startActivity(intent);
            }
        });
    }
}