package com.pace.lumbar.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.pace.lumbar.R;

import java.util.Arrays;
import java.util.List;

public class ClientProfCreation extends AppCompatActivity {

    private EditText namePt;
    private EditText email;
    private EditText phoneNumPt;
    private EditText emailPt;
    private Button profSelectBtn;
    private ImageButton backbtn;
    private ImageView profImageView;
    private Button createProfBtn;
    private EditText address;
    private EditText pwdPtOne;
    private EditText pwdPtTwo;
    private Uri imageUri;
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
        address = findViewById(R.id.etAddressClient);
        pwdPtOne = findViewById(R.id.etPassword);
        pwdPtTwo = findViewById(R.id.etconfirm);


//        //Initialize places
//        Places.initialize(getApplicationContext(), "AIzaSyAf_V5-KkmEPO4OMPrOoT4V4IQKI_OJflI");
//
//        //Set EditText non focusable
//        address.setFocusable(false);
//        address.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Initialize place field list
//                List<Place.Field> fieldlist = Arrays.asList(Place.Field.ADDRESS,
//                        Place.Field.LAT_LNG,Place.Field.NAME);
//                //Create intent
//                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
//                        fieldlist).build(ClientProfCreation.this);
//                //Start activity result
//                startActivityForResult(intent, 100);
//            }
//        });

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
                        isNotEmpty(address) && isNotEmpty(pwdPtOne) && isNotEmpty(pwdPtTwo) &&
                        isNotEmpty(emailPt) ) {
                    if (!pwdPtOne.getText().toString().equals(pwdPtTwo.getText().toString())) {
                        CharSequence incompleteMsg = "Creation failed: passwords must match";
                        Toast.makeText(getApplicationContext(), incompleteMsg,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        openActivity2(namePt.getText().toString(), phoneNumPt.getText().toString(),
                                emailPt.getText().toString(), address.getText().toString(),
                                pwdPtOne.getText().toString(), imageUri);
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

//        if(requestCode == 100 && resultCode == RESULT_OK){
//            //When success, initialize place
//            Place place = Autocomplete.getPlaceFromIntent(data);
//            //Set address on etAddress
//            address.setText(place.getAddress());
//        } else if (resultCode == AutocompleteActivity.RESULT_ERROR){
//            //When error
//            //Initialize status
//            Status status = Autocomplete.getStatusFromIntent(data);
//            Toast.makeText(getApplicationContext(), status.getStatusMessage(),Toast.LENGTH_SHORT).show();
//        }

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                imageUri = data.getData();
                if (null != imageUri) {
                    // update the preview image in the layout
                    profImageView.setImageURI(imageUri);
                }
            }
        }
    }
    private void openActivity2(String name, String phone, String email,
                               String address, String password, Uri imageUri) {
        Intent intent = new Intent(this, ClientCaseCreate.class);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("address", address);
        intent.putExtra("password", password);
        intent.putExtra("imageUri", imageUri);

        startActivity(intent);
    }
}