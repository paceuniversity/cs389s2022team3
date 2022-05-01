package com.pace.lumbar.account;

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

import com.pace.lumbar.R;

public class LawLogPg1 extends AppCompatActivity {
    private EditText namePt;
    private EditText emailPt;
    private EditText phonePt;
    private Button profSelectBtn;
    private ImageView profImageView;
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
        emailPt = findViewById(R.id.emailAddress);
        profSelectBtn = findViewById(R.id.uploadImgBtn);
        profImageView = findViewById(R.id.profileImgView);
        phonePt = findViewById(R.id.phone);
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
                if (isNotEmpty(namePt) &&
                        isNotEmpty(emailPt) && isNotEmpty(phonePt) &&
                        isNotEmpty(pwdPtOne) && isNotEmpty(pwdPtTwo)) {
                    if (pwdPtOne.getText().toString().equals(pwdPtTwo.getText().toString()) == false) {
                        CharSequence incompleteMsg = "Passwords does not match!";
                        Toast.makeText(getApplicationContext(), incompleteMsg,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        CharSequence incompleteMsg = "Credentials Correct!";
                        Toast.makeText(getApplicationContext(), incompleteMsg,
                                Toast.LENGTH_SHORT).show();
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(LawLogPg1.this, LawLogPg2.class);
                                intent.putExtra("name", namePt.getText().toString());
                                intent.putExtra("email", emailPt.getText().toString());
                                intent.putExtra("phoneNum", phonePt.getText().toString());
                                intent.putExtra("password", pwdPtOne.getText().toString());
                                intent.putExtra("profImgUri", imageUri);
                                startActivity(intent);
                            }
                        });
                    }
                } else{
                    CharSequence incompleteMsg = "Form is incomplete";
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