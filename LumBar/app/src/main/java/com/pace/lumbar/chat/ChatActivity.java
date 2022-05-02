package com.pace.lumbar.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pace.lumbar.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mChatAdapter;
    private RecyclerView.LayoutManager mChatLayoutManager;
    private String currentUserID, matchID, chatID, userType;

    private EditText mSendEditText;
    private Button mSendButton;

    private DatabaseReference mDatabaseUser, mDatabaseChat, mDatabaseUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        matchID = getIntent().getExtras().getString("matchId");
        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Client").child(currentUserID).child("connections").child("matches").child(matchID).child("ChatID");
        mDatabaseUser2 = FirebaseDatabase.getInstance().getReference().child("Lawyer").child(currentUserID).child("connections").child("matches").child(matchID).child("ChatID");

        mRecylerView = (RecyclerView) findViewById(R.id.recylcleView);
        mRecylerView.setNestedScrollingEnabled(false);
        mRecylerView.setHasFixedSize(false);
        mChatLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mRecylerView.setLayoutManager(mChatLayoutManager);
        mChatAdapter = new ChatAdapter(getDataSetMatches(), ChatActivity.this);
        mRecylerView.setAdapter(mChatAdapter);

        mSendEditText = (EditText) findViewById(R.id.message);
        mSendButton = (Button) findViewById(R.id.send);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String sentMSG = mSendEditText.getText().toString();

        if(!sentMSG.isEmpty()){
            DatabaseReference newMsgDB = mDatabaseChat.push();

            Map newMSG = new HashMap();
            newMSG.put("createdByUser", currentUserID);
            newMSG.put("text", sentMSG);

            newMsgDB.setValue(newMSG);
        }

        mSendEditText.setText(null);
    }

    private void getChatID() {
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    chatID = snapshot.getValue().toString();
                    userType="Client";
                    mDatabaseChat = mDatabaseChat.child(chatID);
                    getChatMessage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabaseUser2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    chatID = snapshot.getValue().toString();
                    userType="Lawyer";
                    mDatabaseChat = mDatabaseChat.child(chatID);
                    getChatMessage();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getChatMessage() {
        mDatabaseChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String message = null;
                    String createdByUser = null;

                    if(snapshot.child("text").getValue() != null){
                        message = snapshot.child("text").getValue().toString();
                    }
                    if(snapshot.child("createdByUser").getValue() != null){
                        createdByUser = snapshot.child("createdByUser").getValue().toString();
                    }
                    if(message != null && createdByUser != null){
                        Boolean currentUserBoolean = false;
                        if(createdByUser.equals(currentUserID)){
                            currentUserBoolean = true;
                        }
                        ChatObject newMessage = new ChatObject(message, currentUserBoolean);
                        resultChat.add(newMessage);
                        mChatAdapter.notifyDataSetChanged();
                    }
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

    private ArrayList<ChatObject> resultChat = new ArrayList<>();

    private List<ChatObject> getDataSetMatches() {
        return resultChat;}
}