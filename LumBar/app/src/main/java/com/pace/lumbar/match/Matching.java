package com.pace.lumbar.match;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.pace.lumbar.R;
import com.pace.lumbar.fragments.adapters.arrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Matching extends AppCompatActivity {

    private Cards cards_data[];
    private com.pace.lumbar.fragments.adapters.arrayAdapter arrayAdapter;
    private int i;
    private String userType;
    private String oppositeUserType;

    private FirebaseAuth mAuth;

    private String currentUID;

    private DatabaseReference usersDB;
    ListView listView;
    List<Cards> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_system);

        usersDB = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        currentUID = mAuth.getCurrentUser().getUid();

        checkUserType();

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
                String email = obj.getEmail();
                usersDB.child(email).child("connections").child("no").child("currentUID").setValue(true);
                makeToast(Matching.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                String email = obj.getEmail();
                usersDB.child(email).child("connections").child("yes").child("currentUID").setValue(true);
                isConnectionMatch(email);
                makeToast(Matching.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                //getOppositeUserTypes();

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(Matching.this, "Clicked!");
            }
        });

    }

    private void isConnectionMatch(String email) {
        DatabaseReference currentUserConnectionDb = usersDB.child(currentUID).child("connections").child("yes").child(email);
        currentUserConnectionDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    makeToast(Matching.this, "New Connection");

                    String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();
                    usersDB.child(snapshot.getKey()).child("connections").child("matches").child(currentUID).child("ChatId").setValue(key);
                    usersDB.child(currentUID).child("connections").child("matches").child(snapshot.getKey()).child("ChatId").setValue(key);

//                    usersDB.child(snapshot.getKey()).child("connections").child("matches").child(currentUID).setValue(true);
//                    usersDB.child(currentUID).child("connections").child("matches").child(snapshot.getKey()).setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }


    public void checkUserType(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Check Client
//        DatabaseReference clientDB = FirebaseDatabase.getInstance().getReference().child("users").child(user.getEmail());
        String userID = user.getUid();
        DatabaseReference userDb = usersDB.child(userID);

        userDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.getKey().equals(user.getUid())){
                    if(snapshot.exists()){
                        if(snapshot.child("Client") != null){
                            userType = snapshot.child("Client").getValue().toString();
                            oppositeUserType = "Lawyer";
                            switch(userType){
                                case "Client":
                                    oppositeUserType = "Lawyer";
                                    break;
                                case "Lawyer":
                                    oppositeUserType = "Client";
                                    break;
                            }
                            getOppositeUserTypes();
                        }
                    }
                    //makeToast(Matching.this, userType);
                }
                else{
                    //makeToast(Matching.this, "Client not found!");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        //Check Lawyer
//        DatabaseReference lawyerDB = FirebaseDatabase.getInstance().getReference().child("Lawyer");
//        lawyerDB.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                if(snapshot.getKey().equals(user.getUid())){
//                    userType = "Lawyer";
//                    oppositeUserType = "Client";
//                    //makeToast(Matching.this, userType);
//                    getOppositeUserTypes();
//                }
//                else{
//                    //makeToast(Matching.this, "Lawyer not found!");
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    public void getOppositeUserTypes(){
        usersDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(snapshot.exists() && !snapshot.child("connections").child("no").hasChild(currentUID)
                        && !snapshot.child("connections").child("yes").hasChild(currentUID) && oppositeUserType.equals("Lawyer")){
                    //al.add(snapshot.child("name").getValue().toString());
                    Cards item = new Cards(snapshot.child("name").getValue().toString(), snapshot.child("lawFirm").getValue().toString(),
                            snapshot.child("email").getValue().toString(), snapshot.child("phone").getValue().toString(), snapshot.child("address").getValue().toString(),
                            snapshot.child("topic").getValue().toString(), snapshot.child("website").getValue().toString(), snapshot.child("profileIMGUri").getValue().toString());
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }

                if(snapshot.exists() && !snapshot.child("connections").child("no").hasChild(currentUID)
                        && !snapshot.child("connections").child("yes").hasChild(currentUID) && oppositeUserType.equals("Client")){
                    //al.add(snapshot.child("name").getValue().toString());
                    Cards item = new Cards(snapshot.child("realName").getValue().toString(), snapshot.child("case").child("caseType").getValue().toString(),
                            snapshot.child("email").getValue().toString(), snapshot.child("phoneNumber").getValue().toString(), snapshot.child("city").getValue().toString(),
                            snapshot.child("state").getValue().toString(), snapshot.child("case").child("caseDetails").getValue().toString(), snapshot.child("profileIMGUri").getValue().toString());
                    rowItems.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void likePressed(View view) {
        Toast toast = Toast.makeText(this, R.string.like_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void dislikePressed(View view) {
        Toast toast = Toast.makeText(this, R.string.dislike_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
}
