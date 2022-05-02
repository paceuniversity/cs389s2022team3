package com.pace.lumbar.match;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.pace.lumbar.ProfileActivity;
import com.pace.lumbar.R;
import com.pace.lumbar.adapters.arrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Matching extends AppCompatActivity {

    private Cards cards_data[];
    private com.pace.lumbar.adapters.arrayAdapter arrayAdapter;
    private int i;
    private String userType = "Client";
    private String oppositeUserType = "Lawyer";

    private FirebaseAuth mAuth;

    private String currentUID;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private DatabaseReference currentUserDB = firebaseDatabase.getReference("Client");
    private DatabaseReference usersDB = firebaseDatabase.getReference("Lawyer");
    ListView listView;
    List<Cards> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_system);

        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform Item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.match:
                        startActivity(new Intent(getApplicationContext(),
                                MatchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),
                                ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            currentUID = mAuth.getCurrentUser().getUid();
            Log.d("userID", currentUID);
        }

        getOppositeUserTypes();

        rowItems = new ArrayList<Cards>();
        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems);

        SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Cards obj = (Cards) dataObject;
                String uid = obj.getUid();
                usersDB.child(uid).child("connections").child("no").child(currentUID).setValue(true);
                currentUserDB.child(currentUID).child("connections").child("no").child(uid).setValue(true);
                makeToast(Matching.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                String uid = obj.getUid();
                usersDB.child(uid).child("connections").child("yes").child(currentUID).setValue(true);
                currentUserDB.child(currentUID).child("connections").child("yes").child(uid).setValue(true);

                usersDB.child(uid).child("connections").child("matches").child(currentUID).setValue(true);
                currentUserDB.child(currentUID).child("connections").child("matches").child(uid).setValue(true);
                makeToast(Matching.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) { }

            @Override
            public void onScroll(float scrollProgressPercent) { }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(Matching.this, "Clicked!");
            }
        });
    }

    public void getOppositeUserTypes(){
        DatabaseReference oppositeUserDB = FirebaseDatabase.getInstance().getReference().child(oppositeUserType);
        oppositeUserDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists() && !snapshot.child("connections").child("no").hasChild(currentUID)
                        && !snapshot.child("connections").child("yes").hasChild(currentUID)){

                    Cards item = new Cards(snapshot.child("uid").getValue().toString(), snapshot.child("name").getValue().toString(), snapshot.child("firmName").getValue().toString(),
                            snapshot.child("email").getValue().toString(), snapshot.child("phone").getValue().toString(), snapshot.child("address").getValue().toString(),
                            snapshot.child("topic").getValue().toString(), snapshot.child("website").getValue().toString(), snapshot.child("profileIMGUri").getValue().toString());
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
}
