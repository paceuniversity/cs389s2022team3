package com.pace.lumbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pace.lumbar.databinding.ActivityClientCaseCreateBinding;

public class ClientCaseCreate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_case_create);
        getSupportActionBar().setTitle("Create a Case");

    }
}