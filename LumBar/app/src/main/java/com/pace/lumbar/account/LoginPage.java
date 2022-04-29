package com.pace.lumbar.account;

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
import com.pace.lumbar.AboutPage;
import com.pace.lumbar.Matching;
import com.pace.lumbar.R;

public class LoginPage extends AppCompatActivity {

    private Button loginBtn, clientbtn, lawyerbtn;
    private ImageButton aboutbtn;
    private EditText email, password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.login_page);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(LoginPage.this, Matching.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        //Enter username and pass
        loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
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
                                    if (!task.isSuccessful()) {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        CharSequence completeMsg = "Login successful";
                                        Toast.makeText(getApplicationContext(), completeMsg, Toast.LENGTH_SHORT).show();
                                        openActivity2();
                                    }
                                }
                            });
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

    private void openActivity2() {
        Intent intent = new Intent(LoginPage.this, Matching.class);
        startActivity(intent);
    }
}