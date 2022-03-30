package com.pace.lawyerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LawLogPg2 extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_log_pg2);

        button = (Button)findViewById(R.id.prevbutton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });
    }

    private void openActivity3() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}