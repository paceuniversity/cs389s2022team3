package com.pace.lumbar.account;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.pace.lumbar.match.Matching;
import com.pace.lumbar.R;

public class ClientCaseCreate extends AppCompatActivity {
    private Spinner caseSpinner;
    private Spinner stateSpinner;
    private EditText caseDetails;
    private EditText cityText;
    private Button createCaseBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    private boolean isNotEmpty(EditText edTxt) {
        return edTxt.getText().toString().trim().length() > 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String phone = intent.getExtras().getString("phoneNum");
        String email = intent.getExtras().getString("email");
        String address = intent.getExtras().getString("address");
        String password = intent.getExtras().getString("password");
        String imageUri = intent.getExtras().getString("imageUri");

        setContentView(R.layout.client_case_create);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(ClientCaseCreate.this, Matching.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        caseSpinner = findViewById(R.id.caseSpinner);
        ArrayAdapter<CharSequence> caseAdapter = ArrayAdapter.createFromResource
                (this, R.array.cases, R.layout.spinner_item);
        caseAdapter.setDropDownViewResource(R.layout.spinner_item);
        caseSpinner.setAdapter(caseAdapter);

        stateSpinner = findViewById(R.id.stateSpinnerCase);
        ArrayAdapter<CharSequence>stateAdapter = ArrayAdapter.createFromResource
                (this, R.array.states, R.layout.spinner_item);
        stateAdapter.setDropDownViewResource(R.layout.spinner_item);
        stateSpinner.setAdapter(stateAdapter);

        cityText = findViewById(R.id.cityPT);
        caseDetails = findViewById(R.id.caseDetailsMultiLine);

        createCaseBtn = findViewById(R.id.createCaseButton);
        createCaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (caseSpinner.getSelectedItem() != null && isNotEmpty(caseDetails) &&
                        isNotEmpty(cityText) && stateSpinner.getSelectedItem() != null) {

                    String caseType = caseSpinner.getSelectedItem().toString();
                    String caseDet = caseDetails.getText().toString();
                    Client newUser = new Client(name, phone, email, address, cityText.getText().toString(),
                            stateSpinner.getSelectedItem().toString(), password, caseType, caseDet, imageUri);

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                FirebaseDatabase.getInstance().getReference("Client")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(ClientCaseCreate.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(ClientCaseCreate.this, "Failed to Register. Try again!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }
                    });

                    Intent intent = new Intent(ClientCaseCreate.this, Matching.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        });
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