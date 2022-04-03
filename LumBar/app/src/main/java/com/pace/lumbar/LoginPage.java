package com.pace.lumbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pace.lumbar.aboutpage.AboutPage;
import com.pace.lumbar.aboutpage.SlideAdapter;

public class LoginPage extends AppCompatActivity {

    private Button registerBtn, clientbtn, lawyerbtn;
    private ImageButton aboutbtn;
    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.login_page);

        userName = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);

        registerBtn = findViewById(R.id.login);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(userName) && isNotEmpty(password)){
                    CharSequence incompleteMsg = "Login failed: Username or Password is empty";
                    Toast.makeText(getApplicationContext(), incompleteMsg, Toast.LENGTH_SHORT).show();
                }
                else{
                    Account user = new Account(userName.getText().toString(), password.getText().toString());
                    CharSequence completeMsg = "Login succesful";
                    Toast.makeText(getApplicationContext(), completeMsg,
                        Toast.LENGTH_SHORT).show();
                    openActivity2();
                }
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

        aboutbtn = findViewById(R.id.about);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, AboutPage.class);
                startActivity(intent);
            }
        });
    }

    private boolean isNotEmpty(EditText edTxt) {
        return edTxt.getText().toString().trim().length() == 0;
    }

    private void openActivity2() {
        Intent intent = new Intent(LoginPage.this, HomePage.class);
        startActivity(intent);
    }
}