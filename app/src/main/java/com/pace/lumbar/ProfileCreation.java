package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ProfileCreation extends AppCompatActivity {

    private EditText namePt;
    private EditText agePicker;
    private EditText cityPt;
    private Spinner stateSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_creation);

        namePt = findViewById(R.id.namePlainText);
        agePicker = findViewById(R.id.ageTextNumber);
        cityPt = findViewById(R.id.cityPlainText);

        stateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        stateSpinner.setAdapter(adapter);

    }
}