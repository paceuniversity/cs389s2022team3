package com.pace.lawyerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LawLogPg2 extends AppCompatActivity {
    private Button button;
    private Spinner stateSpinner, caseSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_log_pg2);

        stateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource
                (this, R.array.states, R.layout.spinner_item);
        stateAdapter.setDropDownViewResource(R.layout.spinner_item);
        stateSpinner.setAdapter(stateAdapter);

        caseSpinner = findViewById(R.id.caseSpinner);
        ArrayAdapter<CharSequence> caseAdapter = ArrayAdapter.createFromResource
                (this, R.array.cases, R.layout.spinner_item);
        stateAdapter.setDropDownViewResource(R.layout.spinner_item);
        caseSpinner.setAdapter(caseAdapter);

        button = (Button)findViewById(R.id.prevbutton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });
    }

    private void openActivity3() {
        Intent intent = new Intent(this, LawLogPg1.class);
        startActivity(intent);
    }
}