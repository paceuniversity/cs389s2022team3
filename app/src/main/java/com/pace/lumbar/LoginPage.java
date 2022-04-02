package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LumBar);
        setContentView(R.layout.login_page);
    }
}