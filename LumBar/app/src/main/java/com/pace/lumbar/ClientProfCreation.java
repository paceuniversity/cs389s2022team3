package com.pace.lumbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ClientProfCreation extends AppCompatActivity {

    private EditText namePt;
    private EditText email;
    private EditText phoneNumPt;
    private EditText emailPt;
    private Button profSelectBtn;
    private ImageButton backbtn;
    private ImageView profImageView;
    private Button createProfBtn;
    private EditText userPt;
    private EditText pwdPtOne;
    private EditText pwdPtTwo;
    private int SELECT_PICTURE = 200;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_profile_creation);
        getSupportActionBar().setTitle("Profile Creation");

        mAuth = FirebaseAuth.getInstance();

        namePt = findViewById(R.id.etFname);
        email = findViewById(R.id.emailAddress);
        phoneNumPt = findViewById(R.id.phone);
        emailPt = findViewById(R.id.emailAddress);
        profSelectBtn = findViewById(R.id.uploadImgBtn);
        profImageView = findViewById(R.id.profileImgView);
        createProfBtn = findViewById(R.id.createProfBtn);
        userPt = findViewById(R.id.etUsername);
        pwdPtOne = findViewById(R.id.etPassword);
        pwdPtTwo = findViewById(R.id.etconfirm);

        //allows for profile pic selection from Gallery
        profSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        backbtn = findViewById(R.id.revert);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientProfCreation.this, LoginPage.class);
                startActivity(intent);
            }
        });

        //pressing the create button will create a profile object
        //TODO: store created users in database after Firebase tutorial
        //TODO: exit out of profile creation after profile is saved
        createProfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(namePt) && isNotEmpty(phoneNumPt) &&
                        isNotEmpty(userPt) && isNotEmpty(pwdPtOne) && isNotEmpty(pwdPtTwo) &&
                        isNotEmpty(emailPt) ) {
                    if (pwdPtOne.getText().toString().equals(pwdPtTwo.getText().toString()) == false) {
                        CharSequence incompleteMsg = "Creation failed: passwords must match";
                        Toast.makeText(getApplicationContext(), incompleteMsg,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        openActivity2(namePt.getText().toString(), phoneNumPt.getText().toString(),
                                emailPt.getText().toString(), userPt.getText().toString(),
                                pwdPtOne.getText().toString());
                    }
                } else{
                    CharSequence incompleteMsg = "Creation failed: profile info incomplete";
                    Toast.makeText(getApplicationContext(), incompleteMsg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isNotEmpty(EditText edTxt) {
        return edTxt.getText().toString().trim().length() > 0;
    }

    //image selection code adapted from GeeksForGeeks:
    //https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
    private void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    profImageView.setImageURI(selectedImageUri);
                }
            }
        }
    }
    private void openActivity2(String name, String phoneNum, String email,
                               String username, String password) {
        Intent intent = new Intent(this, ClientCaseCreate.class);
        intent.putExtra("name", name);
        intent.putExtra("phoneNum", phoneNum);
        intent.putExtra("email", email);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}