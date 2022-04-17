package com.pace.lumbar;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    private Button registerBtn, clientbtn, lawyerbtn;
    private ImageButton aboutbtn;
    private EditText email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.login_page);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();

        //Enter username and pass
        registerBtn = findViewById(R.id.login);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() || !validatePassword()){
                    return;
                }
                else{
                    mAuth.signInWithEmailAndPassword(email.getText().toString().trim(),
                            password.getText().toString().trim())
                            .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        CharSequence completeMsg = "Login successful";
                                        Toast.makeText(getApplicationContext(), completeMsg, Toast.LENGTH_SHORT).show();
                                        openActivity2();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    //isUser();
                }
            }
        });

        //Button functions for Client profile creation
        clientbtn = findViewById(R.id.client);
        clientbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, ClientProfCreation.class);
                startActivity(intent);
            }
        });

        //Button functions for Lawyer profile creation
        lawyerbtn = findViewById(R.id.lawyer);
        lawyerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, LawLogPg1.class);
                startActivity(intent);
            }
        });

        //About page button
        aboutbtn = findViewById(R.id.about);
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, AboutPage.class);
                startActivity(intent);
            }
        });
    }

    //Verifies if user credential is in Firebase's Realtime Database
    private void isUser() {
        final String userEnteredUsername = email.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("lawyer");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Method malfunctions somewhere here. It doesn't find the user and password
                //from Firebase and goes straight to the else-statement
                if(snapshot.exists()){

                    email.setError(null);

                    String pswdFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    //Opens Home activity if password correct
                    if(pswdFromDB.equals(userEnteredPassword)){
                        email.setError(null);
                        openActivity2();
                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }

                else{
                    email.setError("No such User exists");
                    email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Checks if username field is empty
    private boolean validateUsername(){
        String val = email.getText().toString();

        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    //Checks if password field is empty
    private boolean validatePassword(){
        String val = password.getText().toString();

        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }

    private boolean isNotEmpty(EditText edTxt) {
        return edTxt.getText().toString().trim().length() == 0;
    }

    private void openActivity2() {
        Intent intent = new Intent(LoginPage.this, HomePage.class);
        startActivity(intent);
    }
}