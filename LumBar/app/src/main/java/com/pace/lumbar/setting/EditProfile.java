package com.pace.lumbar.setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.pace.lumbar.R;

/* Reference: https://www.youtube.com/watch?v=CMAup2xxsJw */

public class EditProfile extends AppCompatActivity {
    private EditText nameTxt, emailTxt, phoneTxt, cityTxt;
    private Spinner stateTxt;
    private Button doneBtn;
    private ImageButton revertBtn;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private DocumentReference documentReference;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String currentuid;
    private ArrayAdapter<CharSequence> stateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        nameTxt = findViewById(R.id.etFname);
        emailTxt = findViewById(R.id.emailAddress);
        phoneTxt = findViewById(R.id.phone);
        cityTxt = findViewById(R.id.cityPlainText);
        stateTxt = findViewById(R.id.stateSpinner);
        stateAdapter = ArrayAdapter.createFromResource
                (this, R.array.states, R.layout.spinner_item);
        stateAdapter.setDropDownViewResource(R.layout.spinner_item);
        stateTxt.setAdapter(stateAdapter);

        doneBtn = findViewById(R.id.done);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        currentuid = user.getUid();
        documentReference = db.collection("user").document(currentuid);

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task){
                if(task.getResult().exists()){
                    String name = task.getResult().getString("realName");
                    String email = task.getResult().getString("email");
                    String phone = task.getResult().getString("phoneNumber");
                    String city = task.getResult().getString("city");
                    String state = task.getResult().getString("state");

                    nameTxt.setText(name);
                    emailTxt.setText(email);
                    phoneTxt.setText(phone);
                    cityTxt.setText(city);
                    int spinnerPosition = stateAdapter.getPosition(state);
                    stateTxt.setSelection(spinnerPosition);
                }
                else{
                    Toast.makeText(EditProfile.this, "No Profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateProfile(){
        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String phone = phoneTxt.getText().toString();
        String city = cityTxt.getText().toString();
        String state = stateTxt.getSelectedItem().toString();

        final DocumentReference dr = db.collection("user").document(currentuid);
        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException{
                //DocumentSnapshot snapshot = transaction.get(dr);

                transaction.update(dr, "realName", name);
                transaction.update(dr, "email", email);
                transaction.update(dr, "phoneNumber", phone);
                transaction.update(dr, "city", city);
                transaction.update(dr, "city", state);

                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditProfile.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}