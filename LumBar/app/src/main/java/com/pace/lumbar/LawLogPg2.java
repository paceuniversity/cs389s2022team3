package com.pace.lumbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LawLogPg2 extends AppCompatActivity {
    private Button backbtn, donebtn;
    private EditText lawFirmText;
    private EditText addressText;
    private EditText cityEditText;
    private Spinner stateSpinner, caseSpinner;
    private EditText emailText;
    private EditText phoneNumText;
    private EditText firmWebsiteText;
    private EditText caseWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_log_pg2);
        Intent intent = getIntent();
        Lawyer lawyer = (Lawyer) intent.getExtras().getSerializable("newUser");

        lawFirmText = findViewById(R.id.Agency);
        addressText = findViewById(R.id.address);
        cityEditText = findViewById(R.id.cityPlainText);
        emailText = findViewById(R.id.emailAddress);
        phoneNumText = findViewById(R.id.phonenum);
        firmWebsiteText = findViewById(R.id.website);
        caseWebsite = findViewById(R.id.caseWeb);

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

        backbtn = (Button)findViewById(R.id.prevbutton);
        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });

        donebtn = (Button)findViewById(R.id.donebutton);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotEmpty(lawFirmText) && isNotEmpty(addressText) &&
                    isNotEmpty(cityEditText) && isNotEmpty(emailText) &&
                    isNotEmpty(phoneNumText) && isNotEmpty(firmWebsiteText)
                    && isNotEmpty(caseWebsite) && stateSpinner.getSelectedItem() != null
                    && caseSpinner.getSelectedItem() != null) {
                    CharSequence completeMsg = "Firm creation successful";
                    Toast.makeText(getApplicationContext(), completeMsg,
                            Toast.LENGTH_SHORT).show();

                    LawFirm firm = new LawFirm(lawFirmText.getText().toString(),
                            addressText.getText().toString(), cityEditText.getText().toString(),
                            stateSpinner.getSelectedItem().toString(),
                            emailText.getText().toString(), phoneNumText.getText().toString(),
                            firmWebsiteText.getText().toString(), caseSpinner.getSelectedItem().toString(),
                            caseWebsite.getText().toString());

                    lawyer.setFirm(firm);
                    DAOLawyer caseDao = new DAOLawyer();
                    caseDao.add(lawyer).addOnSuccessListener(suc->{
                        CharSequence caseCreateMsg = "New user and firm created";
                        Toast.makeText(getApplicationContext(), caseCreateMsg,
                                Toast.LENGTH_SHORT).show();
                    });

                    Intent intent = new Intent(LawLogPg2.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    CharSequence incompleteMsg = "Form is incomplete";
                    Toast.makeText(getApplicationContext(), incompleteMsg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openActivity3() {
        Intent intent = new Intent(this, LawLogPg1.class);
        startActivity(intent);
    }

    private boolean isNotEmpty(EditText edTxt) {
        return edTxt.getText().toString().trim().length() > 0;
    }
}