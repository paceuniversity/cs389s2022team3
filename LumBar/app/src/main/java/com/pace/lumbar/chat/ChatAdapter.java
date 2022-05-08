package com.pace.lumbar.chat;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pace.lumbar.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private List<ChatObject> chatList;
    private Context context;

    public ChatAdapter(List<ChatObject>chatList, Context context){
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatitems, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolder rcv = new ChatViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.getmMessage().setText(chatList.get(position).getMessage());
        if(chatList.get(position).getCurrentUser()){
            holder.getmMessage().setGravity(Gravity.END);
            holder.getmMessage().setTextColor(Color.parseColor("#404040"));
            holder.getmContainer().setBackgroundColor(Color.parseColor("#61538A33"));
        }
        else{
            holder.getmMessage().setGravity(Gravity.START);
            holder.getmMessage().setTextColor(Color.parseColor("#FFFFFF"));
            holder.getmContainer().setBackgroundColor(Color.parseColor("#6A654900"));
        }
    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }
}
