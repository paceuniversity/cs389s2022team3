package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class ProfileCreation extends AppCompatActivity {

    private EditText namePt;
    private EditText agePicker;
    private EditText cityPt;
    private Spinner stateSpinner;
    private Button profSelectBtn;
    private ImageView profImageView;
    private int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_creation);
        getSupportActionBar().setTitle("Profile Creation");

        namePt = findViewById(R.id.namePlainText);
        agePicker = findViewById(R.id.ageTextNumber);
        cityPt = findViewById(R.id.cityPlainText);
        profSelectBtn = findViewById(R.id.uploadImgBtn);
        profImageView = findViewById(R.id.profileImgView);

        //creates the state selection dropdown
        stateSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        stateSpinner.setAdapter(adapter);

        //allows for profile pic selection from Gallery
        profSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }
    //image selection code adapted from GeeksForGeeks:
    //https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
    void imageChooser() {

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
}