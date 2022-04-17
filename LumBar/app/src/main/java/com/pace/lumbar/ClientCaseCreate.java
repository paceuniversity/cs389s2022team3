package com.pace.lumbar;

import android.content.Intent;
import android.graphics.Color;
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

<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
=======
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
>>>>>>> f5219c121c8f71a4f4c517ce4ddd6370c3cd1011
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientCaseCreate extends AppCompatActivity {
    private Spinner caseSpinner;
    private Spinner stateSpinner;
    private EditText caseDetails;
    private EditText cityText;
    private Button createCaseBtn;
    private FirebaseAuth mAuth;


    private boolean isNotEmpty(EditText edTxt) {
        return edTxt.getText().toString().trim().length() > 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String phoneNum = intent.getExtras().getString("phoneNum");
        String email = intent.getExtras().getString("email");
        String username = intent.getExtras().getString("username");
        String password = intent.getExtras().getString("password");
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.client_case_create);
        RelativeLayout layout1 = new RelativeLayout(this);
        layout1.setBackgroundColor(Color.parseColor("#F8F3E7"));
        mAuth = FirebaseAuth.getInstance();

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


                if (caseSpinner.getSelectedItem() != null &&
                        isNotEmpty(caseDetails)) {
                    Case newCase = new Case(username, caseSpinner.getSelectedItem().toString(),
                            caseDetails.getText().toString());

                    Client newUser = new Client(name, phoneNum, email, cityText.getText().toString(),
                            stateSpinner.getSelectedItem().toString(), username, password, newCase);

                    DAOClient clientDao = new DAOClient();
                    clientDao.add(newUser);

                    mAuth.createUserWithEmailAndPassword(email, password);

                    DAOCase caseDao = new DAOCase();
                    caseDao.add(newCase).addOnSuccessListener(suc->{
                        CharSequence caseCreateMsg = "Case created";
                        Toast.makeText(getApplicationContext(), caseCreateMsg,
                                Toast.LENGTH_SHORT).show();
                    });


                }
            }
        });
    }

}