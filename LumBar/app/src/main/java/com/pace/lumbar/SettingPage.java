package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.pace.lumbar.dupAboutPage.AboutPageDup;
import com.pace.lumbar.fragments.ProfileFragment;

public class SettingPage extends AppCompatActivity {

    private Button signOut, aboutBtn, helpBtn;
    private ImageButton revertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);

        signOut = findViewById(R.id.signout);
        signOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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

        revertBtn = findViewById(R.id.revertToProf);
        revertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingPage.this, ProfileFragment.class);
                startActivity(intent);
            }
        });
    }
}