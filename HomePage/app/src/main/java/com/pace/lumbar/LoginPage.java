package com.pace.lumbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    private Button registerBtn, clientbtn, lawyerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.login_page);

        registerBtn = findViewById(R.id.login);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        clientbtn = findViewById(R.id.client);
        clientbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, ClientProfCreation.class);
                startActivity(intent);
            }
        });

        lawyerbtn = findViewById(R.id.lawyer);
        lawyerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, LawLogPg1.class);
                startActivity(intent);
            }
        });
    }
}