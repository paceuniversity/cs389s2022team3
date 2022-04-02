package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.login_page);

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfCreation();
            }
        });
    }

    private void openProfCreation() {
        Intent intent = new Intent(this, ClientProfCreation.class);
        startActivity(intent);
    }
}