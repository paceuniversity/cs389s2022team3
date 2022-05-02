package com.pace.lumbar.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pace.lumbar.R;
import com.pace.lumbar.match.Matching;

public class LawLogPg2 extends AppCompatActivity {
    private Button backbtn, donebtn;
    private EditText lawFirmText;
    private EditText addressText;
    private EditText cityEditText;
    private Spinner stateSpinner, caseSpinner;
    private EditText emailText;
    private EditText phoneNumText;
    private EditText firmWebsiteText;
    private EditText budget;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_log_pg2);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String phone = intent.getExtras().getString("phoneNum");
        String email = intent.getExtras().getString("email");
        String password = intent.getExtras().getString("password");
        String imageUri = intent.getExtras().getString("imageUri");

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(LawLogPg2.this, Matching.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        lawFirmText = findViewById(R.id.Agency);
        addressText = findViewById(R.id.address);
        cityEditText = findViewById(R.id.cityPlainText);
        emailText = findViewById(R.id.emailAddress);
        phoneNumText = findViewById(R.id.phonenum);
        firmWebsiteText = findViewById(R.id.website);
        budget = findViewById(R.id.budgetEDIT);

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
                    && isNotEmpty(budget) && stateSpinner.getSelectedItem() != null
                    && caseSpinner.getSelectedItem() != null) {

                    String caseType = caseSpinner.getSelectedItem().toString();

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LawLogPg2.this, task -> {
                        if(!task.isSuccessful()){
                            CharSequence completeMsg = "Failed to Connect to Database";
                            Toast.makeText(getApplicationContext(), completeMsg,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lumbar-af6f0-default-rtdb.firebaseio.com/").child("Lawyer").child(userId);

                            Lawyer lawyer;

//                            if(imageUri == null){
//                                lawyer = new Lawyer(userId, name, email, phone, password, "", lawFirmText.getText().toString(),
//                                        addressText.getText().toString(), cityEditText.getText().toString(),
//                                        stateSpinner.getSelectedItem().toString(),
//                                        emailText.getText().toString(), phoneNumText.getText().toString(),
//                                        firmWebsiteText.getText().toString(), caseType, budget.getText().toString());
//                            }
//                            else{
                                lawyer = new Lawyer(userId, name, email, phone, password, "", lawFirmText.getText().toString(),
                                        addressText.getText().toString(), cityEditText.getText().toString(),
                                        stateSpinner.getSelectedItem().toString(),
                                        emailText.getText().toString(), phoneNumText.getText().toString(),
                                        firmWebsiteText.getText().toString(), caseType, budget.getText().toString());
//                            }

                            currentUserDb.setValue(lawyer);
                            CharSequence caseCreateMsg = "New user and firm created";
                            Toast.makeText(getApplicationContext(), caseCreateMsg,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(LawLogPg2.this, Matching.class);
                    startActivity(intent);
                    finish();
                    return;
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

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}