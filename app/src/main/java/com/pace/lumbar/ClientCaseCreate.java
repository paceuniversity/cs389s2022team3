package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ClientCaseCreate extends AppCompatActivity {
    private Spinner caseSpinner;
    private Button createCaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_LumBar);
        setContentView(R.layout.client_case_create);
        RelativeLayout layout1 = new RelativeLayout(this);
        layout1.setBackgroundColor(Color.parseColor("#F8F3E7"));

        caseSpinner = findViewById(R.id.caseSpinner);
        ArrayAdapter<CharSequence>caseAdapter = ArrayAdapter.createFromResource
                (this, R.array.cases, R.layout.spinner_item);
        caseAdapter.setDropDownViewResource(R.layout.spinner_item);
        caseSpinner.setAdapter(caseAdapter);

        createCaseBtn = findViewById(R.id.createCaseButton);
        createCaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence caseCreateMsg = "Case created";
                Toast.makeText(getApplicationContext(), caseCreateMsg,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}