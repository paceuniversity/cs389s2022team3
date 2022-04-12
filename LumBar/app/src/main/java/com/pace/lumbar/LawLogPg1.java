package com.pace.lumbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LawLogPg1 extends AppCompatActivity {
    private EditText namePt;
    private EditText agePicker;
    private EditText emailPt;
    private EditText phonePt;
    private Button profSelectBtn;
    private ImageView profImageView;
    private EditText userPt;
    private EditText pwdPtOne;
    private EditText pwdPtTwo;
    private final int SELECT_PICTURE = 200;
    private Button next;
    private Uri imageUri;
    private ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_log_pg1);
        namePt = findViewById(R.id.etFname);
        agePicker = findViewById(R.id.bday);
        emailPt = findViewById(R.id.emailAddress);
        profSelectBtn = findViewById(R.id.uploadImgBtn);
        profImageView = findViewById(R.id.profileImgView);
        phonePt = findViewById(R.id.phone);
        userPt = findViewById(R.id.etUsername);
        pwdPtOne = findViewById(R.id.etPassword);
        pwdPtTwo = findViewById(R.id.etconfirm);
        next = findViewById(R.id.next);

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
                Intent intent = new Intent(LawLogPg1.this, LoginPage.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(namePt) && isNotEmpty(agePicker) &&
                        isNotEmpty(emailPt) && isNotEmpty(phonePt) &&
                        isNotEmpty(userPt) && isNotEmpty(pwdPtOne) && isNotEmpty(pwdPtTwo)) {
                    if (pwdPtOne.getText().toString().equals(pwdPtTwo.getText().toString()) == false) {
                        CharSequence incompleteMsg = "Passwords does not match!";
                        Toast.makeText(getApplicationContext(), incompleteMsg,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Lawyer newUser = new Lawyer(namePt.getText().toString(),
                                Integer.parseInt(agePicker.getText().toString()),
                                emailPt.getText().toString(), Integer.parseInt(phonePt.getText().toString()),
                                userPt.getText().toString(), pwdPtOne.getText().toString());

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("lawyer");
                        myRef.setValue(newUser);

                        CharSequence incompleteMsg = "Credentials Correct!";
                        Toast.makeText(getApplicationContext(), incompleteMsg,
                                Toast.LENGTH_SHORT).show();
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(LawLogPg1.this, LawLogPg2.class);
                                startActivity(intent);
                            }
                        });

                    }
                } else{
                    CharSequence incompleteMsg = "Profile info incomplete!";
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
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                imageUri = data.getData();
                profImageView.setImageURI(imageUri);
            }
        }
    }
}