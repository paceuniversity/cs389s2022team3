package com.pace.lumbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    private Button registerBtn, clientbtn, lawyerbtn;
    private ImageButton aboutbtn;
    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.Theme_LumBar); no appbar for now
        setContentView(R.layout.login_page);

        userName = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);

        //Enter username and pass
        registerBtn = findViewById(R.id.login);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() || !validatePassword()){
                    return;
                }
                else{
                    CharSequence completeMsg = "Login successful";
                    Toast.makeText(getApplicationContext(), completeMsg, Toast.LENGTH_SHORT).show();
                    openActivity2();
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
        final String userEnteredUsername = userName.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("lawyer");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Method malfunctions somewhere here. It doesn't find the user and password
                //from Firebase and goes straight to the else-statement
                if(snapshot.exists()){

                    userName.setError(null);

                    String pswdFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    //Opens Home activity if password correct
                    if(pswdFromDB.equals(userEnteredPassword)){
                        userName.setError(null);
                        openActivity2();
                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }

                else{
                    userName.setError("No such User exists");
                    userName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Checks if username field is empty
    private boolean validateUsername(){
        String val = userName.getText().toString();

        if(val.isEmpty()){
            userName.setError("Field cannot be empty");
            return false;
        }
        else{
            userName.setError(null);
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