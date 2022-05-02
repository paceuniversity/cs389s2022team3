package com.pace.lumbar.match;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pace.lumbar.R;
import com.pace.lumbar.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

/*Reference: https://www.youtube.com/watch?v=9dC4w04AuOs&list=PLxabZQCAe5fio9dm1Vd0peIY6HLfo5MCf&index=16*/

public class MatchActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mMatchesAdapter;
    private RecyclerView.LayoutManager mMatchesLayoutManager;
    private String currentUserID, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);


        //Initialize and Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.match);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.match:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                Matching.class));
                        overridePendingTransition(0, 0);
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

        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mRecylerView = (RecyclerView) findViewById(R.id.recylcleView);
        mRecylerView.setNestedScrollingEnabled(false);
        mRecylerView.setHasFixedSize(true);
        mMatchesLayoutManager = new LinearLayoutManager(MatchActivity.this);
        mRecylerView.setLayoutManager(mMatchesLayoutManager);
        mMatchesAdapter = new MatchesAdapter(getDataSetMatches(), MatchActivity.this);
        mRecylerView.setAdapter(mMatchesAdapter);

        getUserMatchID();

    }

    private void getUserMatchID() {
        DatabaseReference matchDb = FirebaseDatabase.getInstance().getReference().child("Client").child(currentUserID).child("connections").child("matches");
        DatabaseReference lawyerMatchesDb = FirebaseDatabase.getInstance().getReference().child("Lawyer").child(currentUserID).child("connections").child("matches");

        matchDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot match : snapshot.getChildren()){
                        userType = "Lawyer";
                        FetchMatchInformation(match.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lawyerMatchesDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot match : snapshot.getChildren()){
                        userType = "Client";
                        FetchMatchInformation(match.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void FetchMatchInformation(String key) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child(userType).child(key);

        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String userId = snapshot.getKey();
                    String name = "";
                    String profileIMGUri = "";
                    if(snapshot.child("name").getValue() != null){
                        name = snapshot.child("name").getValue().toString();
                    }
                    if(snapshot.child("profileIMGUri").getValue() != null || !snapshot.child("profileIMGUri").getValue().toString().equals("")){
                        profileIMGUri = snapshot.child("profileIMGUri").getValue().toString();
                    }

                    MatchesObject obj = new MatchesObject(userId, name, profileIMGUri);
                    resultMatches.add(obj);
                    mMatchesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<MatchesObject> resultMatches = new ArrayList<MatchesObject>();

    private List<MatchesObject> getDataSetMatches() {
        return resultMatches;
    }
}