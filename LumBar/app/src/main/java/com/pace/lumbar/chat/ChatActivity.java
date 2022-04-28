package com.pace.lumbar.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pace.lumbar.R;
import com.pace.lumbar.match.MatchActivity;
import com.pace.lumbar.match.MatchesAdapter;
import com.pace.lumbar.match.MatchesObject;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mChatAdapter;
    private RecyclerView.LayoutManager mChatLayoutManager;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mRecylerView = (RecyclerView) findViewById(R.id.recylcleView);
        mRecylerView.setNestedScrollingEnabled(false);
        mRecylerView.setHasFixedSize(true);
        mChatLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mRecylerView.setLayoutManager(mChatLayoutManager);
        mChatAdapter = new ChatAdapter(getDataSetMatches(), ChatActivity.this);
        mRecylerView.setAdapter(mChatAdapter);
    }

    private ArrayList<ChatObject> resultChat = new ArrayList<>();

    private List<ChatObject> getDataSetMatches() {
        return resultChat;
    }
}